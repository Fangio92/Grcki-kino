package com.dizdarevic.grckikino.repo

import android.app.Application
import com.dizdarevic.grckikino.repo.model.GrckiKino
import retrofit2.Call

class NetworkRepository(
    private val api: API,
    private val context: Application
) {
    fun get20Rounds(): Call<GrckiKino>? =
        api.get20Rounds()
    fun getSpecificRound(drawId: Int) =
        api.getSpecificRound(drawId)
}