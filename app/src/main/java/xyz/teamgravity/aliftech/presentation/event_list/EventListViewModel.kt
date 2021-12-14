package xyz.teamgravity.aliftech.presentation.event_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import xyz.teamgravity.aliftech.core.Resource
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.domain.use_case.GetEventsUseCase
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 3
        private const val INITIAL_SIZE = PAGE_SIZE * 3
    }

    private val _state = MutableStateFlow(EventListState())
    val state: StateFlow<EventListState> = _state

    private var events: List<EventModel>? = null
    private var currentSize = 0

    private var _finished = false
    val finished get() = _finished

    private var _loading = false
    val loading get() = _loading

    init {
        getEvents()
    }

    fun getEvents() {
        getEventsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    events = result.data
                    events?.let { events ->
                        if (events.size > INITIAL_SIZE) { // there is more item
                            currentSize = INITIAL_SIZE
                            _state.emit(EventListState(events = events.subList(0, currentSize)))
                        } else { // there is less item
                            currentSize = events.size
                            _state.emit(EventListState(events = events))
                            _finished = true
                        }
                    } ?: run { // there is no data
                        _state.emit(EventListState(events = emptyList()))
                    }
                }

                is Resource.Error -> {
                    _state.emit(EventListState(error = result.message ?: "Unknown error occurred"))
                }

                is Resource.Loading -> {
                    _state.emit(EventListState(loading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun nextPage() {
        viewModelScope.launch {
            events?.let { events ->

                _loading = true

                val difference = events.size - (currentSize + PAGE_SIZE)

                when {
                    difference == 0 -> {
                        currentSize += PAGE_SIZE
                        _state.emit(EventListState(events = events.subList(0, currentSize)))
                        _finished = true
                    }

                    difference > 0 -> {
                        currentSize += PAGE_SIZE
                        _state.emit(EventListState(events = events.subList(0, currentSize)))
                    }

                    difference < 0 -> {
                        currentSize += (PAGE_SIZE - abs(difference))
                        _state.emit(EventListState(events = events.subList(0, currentSize)))
                        _finished = true
                    }
                }

                _loading = false
            }
        }
    }
}