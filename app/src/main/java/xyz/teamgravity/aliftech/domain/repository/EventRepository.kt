package xyz.teamgravity.aliftech.domain.repository

import xyz.teamgravity.aliftech.data.local.EventLocalDto
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto

interface EventRepository {

    suspend fun getEventsRemote(): EventResponseDto

    suspend fun getEventsLocal(): List<EventLocalDto>
}