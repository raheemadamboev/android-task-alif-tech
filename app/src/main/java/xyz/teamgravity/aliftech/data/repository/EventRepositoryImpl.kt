package xyz.teamgravity.aliftech.data.repository

import xyz.teamgravity.aliftech.data.local.dao.EventDao
import xyz.teamgravity.aliftech.data.local.dto.EventLocalDto
import xyz.teamgravity.aliftech.data.remote.api.EventApi
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto
import xyz.teamgravity.aliftech.domain.repository.EventRepository

class EventRepositoryImpl(
    private val api: EventApi,
    private val dao: EventDao
) : EventRepository {

    override suspend fun insertEventsLocal(events: List<EventLocalDto>) {
        dao.insertAll(events)
    }

    override suspend fun deleteAllEventsLocal() {
        dao.deleteAll()
    }

    override suspend fun getEventsRemote(): EventResponseDto {
        return api.getEvents()
    }

    override suspend fun getEventsLocal(): List<EventLocalDto> {
        return dao.getEvents()
    }
}