package xyz.teamgravity.aliftech.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.teamgravity.aliftech.data.remote.api.EventApi
import xyz.teamgravity.aliftech.data.repository.EventRepositoryImpl
import xyz.teamgravity.aliftech.domain.repository.EventRepository
import xyz.teamgravity.aliftech.domain.use_case.GetEventsUseCase
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
    fun provideEventRepository(api: EventApi): EventRepository = EventRepositoryImpl(api)

    @Provides
    fun provideGetEventsUseCase(repository: EventRepository) = GetEventsUseCase(repository)
}