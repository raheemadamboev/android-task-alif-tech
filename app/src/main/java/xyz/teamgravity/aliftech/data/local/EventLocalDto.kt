package xyz.teamgravity.aliftech.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.teamgravity.aliftech.domain.model.EventModel

@Entity(tableName = EventDatabaseConst.TABLE_EVENT)
data class EventLocalDto(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val icon: String = "",
    val url: String,
    val endDate: String
)

fun EventLocalDto.toEventModel(): EventModel = EventModel(
    name = name,
    iconUrl = icon,
    contentUrl = url,
    endDate = endDate
)