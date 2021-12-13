package xyz.teamgravity.aliftech.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<EventLocalDto>)

    @Query("SELECT * FROM ${EventDatabaseConst.TABLE_EVENT} ORDER BY id ASC")
    suspend fun getEvents(): Flow<List<EventLocalDto>>
}