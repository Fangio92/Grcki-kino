package com.dizdarevic.grckikino.repo

import android.app.Application
import com.dizdarevic.grckikino.repo.model.GrckiKino
import retrofit2.Call

class NetworkRepository(
    private val api: API,
    private val context: Application
) {
    fun get20Rounds(): Call<GrckiKino>? {
        val hashMap = HashMap<String, String>()
        hashMap["Content-Type"] = "application/json"
        return api.get20Rounds(hashMap)
    }
}