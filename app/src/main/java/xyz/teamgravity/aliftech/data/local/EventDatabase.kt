package xyz.teamgravity.aliftech.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EventLocalDto::class],
    version = EventDatabaseConst.VERSION,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}