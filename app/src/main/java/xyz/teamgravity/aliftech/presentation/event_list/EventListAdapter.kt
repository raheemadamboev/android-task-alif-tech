package xyz.teamgravity.aliftech.presentation.event_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import xyz.teamgravity.aliftech.databinding.CardEventBinding
import xyz.teamgravity.aliftech.domain.model.EventModel

class EventListAdapter(diff: EventListDiff) : ListAdapter<EventModel, EventListViewHolder>(diff) {

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        return EventListViewHolder(CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}