package xyz.teamgravity.aliftech.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.teamgravity.aliftech.data.local.dao.EventDao
import xyz.teamgravity.aliftech.domain.model.EventModel

@Database(
    entities = [EventModel::class],
    version = EventDatabaseConst.VERSION,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}