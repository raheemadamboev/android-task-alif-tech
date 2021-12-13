package xyz.teamgravity.aliftech.domain.use_case

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import xyz.teamgravity.aliftech.core.Resource
import xyz.teamgravity.aliftech.data.remote.dto.toEventModel
import xyz.teamgravity.aliftech.domain.exception.EventEmptyException
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.domain.repository.EventRepository

class GetEventsUseCase(private val repository: EventRepository) {

    operator fun invoke(): Flow<Resource<PagingSource<Int, EventModel>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getEventsRemote()
            if (response.response.isNullOrEmpty()) throw EventEmptyException("Events are empty in the response")
            val localEvents = response.response.map { it.toEventModel() }

            repository.deleteAllEventsLocal()
            repository.insertAllEventsLocal(events = localEvents)

            val events = repository.getEventsLocal()
            emit(Resource.Success(data = events))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unknown error occurred"))
        }
    }
}