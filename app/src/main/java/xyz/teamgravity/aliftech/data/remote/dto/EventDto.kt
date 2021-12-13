package xyz.teamgravity.aliftech.data.remote.dto


import com.google.gson.annotations.SerializedName

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