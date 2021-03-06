package xyz.teamgravity.aliftech.injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.teamgravity.aliftech.data.local.dao.EventDao
import xyz.teamgravity.aliftech.data.local.database.EventDatabase
import xyz.teamgravity.aliftech.data.local.database.EventDatabaseConst
import xyz.teamgravity.aliftech.data.remote.api.EventApi
import xyz.teamgravity.aliftech.data.repository.EventRepositoryImpl
import xyz.teamgravity.aliftech.domain.repository.EventRepository
import xyz.teamgravity.aliftech.domain.use_case.GetEventsUseCase
import xyz.teamgravity.aliftech.presentation.event_list.EventListAdapter
import xyz.teamgravity.aliftech.presentation.event_list.EventListDiff
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideEventApi(): EventApi = Retrofit.Builder()
        .baseUrl(EventApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EventApi::class.java)

    @Provides
    fun provideEventRepository(api: EventApi, dao: EventDao): EventRepository = EventRepositoryImpl(api, dao)

    @Provides
    fun provideGetEventsUseCase(repository: EventRepository) = GetEventsUseCase(repository)

    @Provides
    @Singleton
    fun provideEventDatabase(app: Application): EventDatabase =
        Room.databaseBuilder(app, EventDatabase::class.java, EventDatabaseConst.NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideEventDao(database: EventDatabase): EventDao = database.eventDao()

    @Provides
    @Singleton
    fun provideEventListDiff(): EventListDiff = EventListDiff()

    @Provides
    fun provideEventListAdapter(diff: EventListDiff): EventListAdapter = EventListAdapter(diff)
}