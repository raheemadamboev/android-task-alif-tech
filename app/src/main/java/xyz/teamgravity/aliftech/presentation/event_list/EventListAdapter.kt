package xyz.teamgravity.aliftech.presentation.event_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.teamgravity.aliftech.databinding.CardEventBinding
import xyz.teamgravity.aliftech.domain.model.EventModel
import xyz.teamgravity.aliftech.presentation.extension.centerCrop

class EventListAdapter(diff: EventListDiff) : ListAdapter<EventModel, EventListAdapter.EventListViewHolder>(diff) {

    var listener: EventListListener? = null

    inner class EventListViewHolder(
        private val binding: CardEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onEventClick(getItem(position).contentUrl)
                }
            }
        }

        fun bind(model: EventModel) {
            binding.apply {
                Glide.with(root.context).centerCrop(model.iconUrl, iconI)
                nameT.text = model.name
                endDateT.text = model.endDate
            }
        }
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        return EventListViewHolder(CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}