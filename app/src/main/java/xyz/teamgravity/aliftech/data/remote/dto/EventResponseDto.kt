package xyz.teamgravity.aliftech.data.remote.dto


import com.google.gson.annotations.SerializedName

data class EventResponseDto(
    @SerializedName("data") val response: List<EventDto>,
    @SerializedName("total") val total: String
)