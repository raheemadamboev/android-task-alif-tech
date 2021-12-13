package xyz.teamgravity.aliftech.data.repository

import xyz.teamgravity.aliftech.data.remote.api.EventApi
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto
import xyz.teamgravity.aliftech.domain.repository.EventRepository

class EventRepositoryImpl(
    private val api: EventApi
) : EventRepository {

    override suspend fun getEvents(): EventResponseDto {
        return api.getEvents()
    }
}