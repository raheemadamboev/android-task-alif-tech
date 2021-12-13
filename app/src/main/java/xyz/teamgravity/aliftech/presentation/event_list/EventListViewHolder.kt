package xyz.teamgravity.aliftech.presentation.event_list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.teamgravity.aliftech.databinding.CardEventBinding
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.presentation.extension.centerCrop

class EventListViewHolder(private val binding: CardEventBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: EventModel) {
        binding.apply {
            Glide.with(root.context).centerCrop(model.iconUrl, iconI)
            nameT.text = model.name
            endDateT.text = model.endDate
        }
    }
}