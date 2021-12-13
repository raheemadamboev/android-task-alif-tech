package xyz.teamgravity.aliftech.data.remote.dto


import com.google.gson.annotations.SerializedName
import xyz.teamgravity.aliftech.domain.model.EventModel

data class EventDto(
    @SerializedName("endDate") val endDate: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("loginRequired") val loginRequired: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("objType") val objType: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("url") val url: String,
    @SerializedName("venue") val venue: VenueDto
)

fun EventDto.toEventModel(): EventModel = EventModel(
    name = name,
    iconUrl = icon,
    contentUrl = url,
    endDate = endDate
)