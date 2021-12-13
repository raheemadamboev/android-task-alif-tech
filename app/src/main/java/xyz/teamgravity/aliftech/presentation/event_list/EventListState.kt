package xyz.teamgravity.aliftech.presentation.event_list

import androidx.paging.Pager
import xyz.teamgravity.aliftech.domain.model.EventModel

data class EventListState(
    val events: Pager<Int, EventModel>? = null,
    val loading: Boolean = true,
    val error: String? = null,
)
