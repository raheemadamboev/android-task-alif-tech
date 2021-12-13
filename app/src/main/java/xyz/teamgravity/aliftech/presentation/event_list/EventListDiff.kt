package xyz.teamgravity.aliftech.presentation.event_list

import androidx.recyclerview.widget.DiffUtil
import xyz.teamgravity.aliftech.domain.model.EventModel

class EventListDiff : DiffUtil.ItemCallback<EventModel>() {

    override fun areItemsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem == newItem
    }
}