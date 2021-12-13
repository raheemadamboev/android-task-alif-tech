package xyz.teamgravity.aliftech.data.remote.api

import retrofit2.http.GET
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto

interface EventApi {

    companion object {
        const val BASE_URL = "https://guidebook.com/"
    }

    @GET(value = "/service/v2/upcomingGuides")
    suspend fun getEvents(): EventResponseDto
}