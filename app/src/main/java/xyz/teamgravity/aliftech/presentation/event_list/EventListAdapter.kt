package xyz.teamgravity.aliftech.presentation.event_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import xyz.teamgravity.aliftech.databinding.CardEventBinding
import xyz.teamgravity.aliftech.domain.model.EventModel

class EventListAdapter(diff: EventListDiff) : PagingDataAdapter<EventModel, EventListViewHolder>(diff) {

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        return EventListViewHolder(CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}