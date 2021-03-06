package xyz.teamgravity.aliftech.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.teamgravity.aliftech.data.local.database.EventDatabaseConst
import xyz.teamgravity.aliftech.data.local.dto.EventLocalDto

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventLocalDto>)

    @Query("DELETE FROM ${EventDatabaseConst.TABLE_EVENT}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${EventDatabaseConst.TABLE_EVENT} ORDER BY id ASC")
    suspend fun getEvents(): List<EventLocalDto>
}