package com.dizdarevic.grckikino.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

fun <T> MutableLiveData<T>.reinvoke() {
    value = value
}