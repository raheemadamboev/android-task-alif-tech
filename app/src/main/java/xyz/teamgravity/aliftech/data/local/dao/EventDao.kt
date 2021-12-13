package xyz.teamgravity.aliftech.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.teamgravity.aliftech.data.local.database.EventDatabaseConst
import xyz.teamgravity.aliftech.domain.model.EventModel

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventModel>)

    @Query("DELETE FROM ${EventDatabaseConst.TABLE_EVENT}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${EventDatabaseConst.TABLE_EVENT} ORDER BY id ASC")
    fun getEvents(): PagingSource<Int, EventModel>
}