package com.dizdarevic.grckikino.di.network

import com.dizdarevic.grckikino.repo.API
import com.dizdarevic.grckikino.repo.NetworkRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.opap.gr/"

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
    factory { NetworkRepository(get(), get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

fun provideApi(retrofit: Retrofit): API = retrofit.create(API::class.java)