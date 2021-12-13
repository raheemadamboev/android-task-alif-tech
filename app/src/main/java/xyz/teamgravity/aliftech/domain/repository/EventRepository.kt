package xyz.teamgravity.aliftech.domain.repository

import xyz.teamgravity.aliftech.data.local.dto.EventLocalDto
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto

interface EventRepository {

    suspend fun insertAllEventsLocal(events: List<EventLocalDto>)

    suspend fun deleteAllEventsLocal()

    suspend fun getEventsRemote(): EventResponseDto

    suspend fun getEventsLocal(): List<EventLocalDto>
}