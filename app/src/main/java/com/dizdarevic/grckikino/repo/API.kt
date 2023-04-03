package com.dizdarevic.grckikino.repo

import com.dizdarevic.grckikino.repo.model.GrckiKino
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface API {
    @GET("draws/v3.0/1100/upcoming/20")
    fun get20Rounds(@HeaderMap headers: HashMap<String, String>): Call<GrckiKino>?
}