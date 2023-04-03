package com.dizdarevic.grckikino.di

import com.dizdarevic.grckikino.App
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { androidContext() as App }
//    viewModelOf(::viewModel)
}