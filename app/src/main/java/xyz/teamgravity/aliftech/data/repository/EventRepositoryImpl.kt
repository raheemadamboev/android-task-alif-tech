package xyz.teamgravity.aliftech.data.repository

import androidx.paging.PagingSource
import xyz.teamgravity.aliftech.data.local.dao.EventDao
import xyz.teamgravity.aliftech.data.remote.api.EventApi
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.domain.repository.EventRepository

class EventRepositoryImpl(
    private val api: EventApi,
    private val dao: EventDao
) : EventRepository {

    override suspend fun insertAllEventsLocal(events: List<EventModel>) {
        dao.insertAll(events)
    }

    override suspend fun deleteAllEventsLocal() {
        dao.deleteAll()
    }

    override suspend fun getEventsRemote(): EventResponseDto {
        return api.getEvents()
    }

    override suspend fun getEventsLocal(): PagingSource<Int, EventModel> {
        return dao.getEvents()
    }
}