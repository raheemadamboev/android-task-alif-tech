package xyz.teamgravity.aliftech.presentation.event_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import xyz.teamgravity.aliftech.core.Resource
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.domain.use_case.GetEventsUseCase
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val pagingConfig: PagingConfig
) : ViewModel() {

    var dick: Flow<PagingData<EventModel>> = emptyFlow()


    private fun getEvents() {
        getEventsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        dick = Pager(pagingConfig) { result.data }.flow
                    }
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}