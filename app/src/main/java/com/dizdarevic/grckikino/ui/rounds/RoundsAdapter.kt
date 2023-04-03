package com.dizdarevic.grckikino.ui.rounds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dizdarevic.grckikino.databinding.RoundsItemBinding
import com.dizdarevic.grckikino.repo.model.GrckiKino
import com.dizdarevic.grckikino.utils.ViewHolder
import kotlinx.datetime.*

class RoundsAdapter : ListAdapter<GrckiKino.GrckiKinoItem, RoundsAdapter.RoundsViewHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundsViewHolder =
        RoundsViewHolder(RoundsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RoundsViewHolder, position: Int) = holder.bind(getItem(position))

    inner class RoundsViewHolder(
        binding: RoundsItemBinding
    ) : ViewHolder<RoundsItemBinding, GrckiKino.GrckiKinoItem>(binding) {
        override fun RoundsItemBinding.bind(item: GrckiKino.GrckiKinoItem) {
            val roundStartDateTime = Instant.fromEpochMilliseconds(item.drawTime).toLocalDateTime(TimeZone.currentSystemDefault())
            val duration = roundStartDateTime.toInstant(TimeZone.currentSystemDefault()).minus(Clock.System.now())
            tvTime.text = roundStartDateTime.time.toString()
            tvDuration.text = String.format("%02d:%02d", duration.toDateTimePeriod().hours, duration.toDateTimePeriod().minutes)
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<GrckiKino.GrckiKinoItem>() {
            override fun areItemsTheSame(oldItem: GrckiKino.GrckiKinoItem, newItem: GrckiKino.GrckiKinoItem): Boolean = oldItem.drawId == newItem.drawId
            override fun areContentsTheSame(oldItem: GrckiKino.GrckiKinoItem, newItem: GrckiKino.GrckiKinoItem): Boolean = oldItem == newItem
        }
    }
}