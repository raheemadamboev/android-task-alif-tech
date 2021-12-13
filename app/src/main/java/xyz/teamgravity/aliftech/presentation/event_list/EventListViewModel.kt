package xyz.teamgravity.aliftech.presentation.event_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import xyz.teamgravity.aliftech.core.Resource
import xyz.teamgravity.aliftech.domain.use_case.GetEventsUseCase
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EventListState())
    val state: StateFlow<EventListState> = _state

    init {
        getEvents()
    }

    fun getEvents() {
        getEventsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.emit(EventListState(events = result.data ?: emptyList()))
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
}