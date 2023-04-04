package com.dizdarevic.grckikino.ui.talon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizdarevic.grckikino.repo.NetworkRepository
import com.dizdarevic.grckikino.repo.model.GrckiKino
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class TalonViewModel(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    val response = MutableStateFlow<GrckiKino.GrckiKinoItem?>(null)

    private val _errors = Channel<String>(Channel.CONFLATED)
    val errors = _errors.receiveAsFlow()
    fun getRound(drawId: Int) = viewModelScope.launch(Dispatchers.IO) {
        networkRepository.getSpecificRound(drawId)?.enqueue(object : Callback<GrckiKino.GrckiKinoItem?> {
            override fun onResponse(call: Call<GrckiKino.GrckiKinoItem?>, data: Response<GrckiKino.GrckiKinoItem?>) {
                if (data.isSuccessful && data.body() != null) {
                    response.value = data.body()
                    return
                }
                try {
                    setError(JSONObject(data.errorBody()!!.string()).getString("message"))
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<GrckiKino.GrckiKinoItem?>, t: Throwable) {
                setError(t.message.toString())
            }
        })
    }
    private fun setError(message: String) {
        _errors.trySend(message)
        response.value = null
    }
}