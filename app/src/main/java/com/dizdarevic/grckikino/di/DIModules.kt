package com.dizdarevic.grckikino.di

import com.dizdarevic.grckikino.App
import com.dizdarevic.grckikino.ui.rounds.RoundsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
val appModule = module {
    single { androidContext() as App }
    viewModelOf(::RoundsViewModel)
}