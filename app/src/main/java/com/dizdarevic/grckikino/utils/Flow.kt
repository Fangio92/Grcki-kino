package com.dizdarevic.grckikino.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, action: suspend (T) -> Unit) = onEach(action)
    .flowWithLifecycle(lifecycleOwner.lifecycle)
    .launchIn(lifecycleOwner.lifecycleScope)
fun <R> Flow<R>.toStateFlow(coroutineScope: CoroutineScope, initialValue: R) = stateIn(coroutineScope, SharingStarted.Lazily, initialValue)
