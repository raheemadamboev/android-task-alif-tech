package xyz.teamgravity.aliftech.presentation.event_list

import xyz.teamgravity.aliftech.domain.model.EventModel

data class EventListState(
    val events: List<EventModel> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)
