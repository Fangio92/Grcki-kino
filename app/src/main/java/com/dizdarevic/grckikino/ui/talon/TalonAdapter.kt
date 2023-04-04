package com.dizdarevic.grckikino.ui.talon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.dizdarevic.grckikino.R
import com.dizdarevic.grckikino.databinding.TalonItemBinding
import com.dizdarevic.grckikino.utils.ViewHolder

class TalonAdapter(
    private val onClickAction: () -> Unit
) : RecyclerView.Adapter<TalonAdapter.TalonViewHolder>() {
    val list = List(80) { TalonItem(it) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalonViewHolder =
        TalonViewHolder(TalonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TalonAdapter.TalonViewHolder, position: Int) = holder.bind(list[position])

    inner class TalonViewHolder(
        binding: TalonItemBinding
    ) : ViewHolder<TalonItemBinding, TalonItem>(binding) {
        @SuppressLint("SetTextI18n")
        override fun TalonItemBinding.bind(item: TalonItem) {
            tvNumber.text = item.item.toString()
            frame.setOnClickListener {
                item.selected = !item.selected
                if (item.selected) {
                    frame.background = AppCompatResources.getDrawable(root.context, R.drawable.circle)
                } else {
                    frame.background = null
                }
                onClickAction.invoke()
            }
        }
    }

    data class TalonItem(
        var position: Int,
        var item: Int = position + 1,
        var selected: Boolean = false
    )
}