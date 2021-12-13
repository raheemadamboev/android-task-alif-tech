package xyz.teamgravity.aliftech.domain.repository

import androidx.paging.PagingSource
import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto
import xyz.teamgravity.aliftech.domain.model.EventModel

interface EventRepository {

    suspend fun insertAllEventsLocal(events: List<EventModel>)

    suspend fun deleteAllEventsLocal()

    suspend fun getEventsRemote(): EventResponseDto

    suspend fun getEventsLocal(): PagingSource<Int, EventModel>
}