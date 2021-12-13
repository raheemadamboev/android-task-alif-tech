package xyz.teamgravity.aliftech.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.teamgravity.aliftech.data.local.database.EventDatabaseConst

@Entity(tableName = EventDatabaseConst.TABLE_EVENT)
data class EventModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val iconUrl: String = "",
    val contentUrl: String = "",
    val endDate: String = ""
)
