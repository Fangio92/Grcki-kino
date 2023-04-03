package com.dizdarevic.grckikino.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolder<VB : ViewBinding, M>(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: M) = binding.bind(item)
    protected abstract fun VB.bind(item: M)
}