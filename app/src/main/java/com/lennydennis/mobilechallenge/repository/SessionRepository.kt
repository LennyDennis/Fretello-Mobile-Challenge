package com.lennydennis.mobilechallenge.repository

import com.lennydennis.mobilechallenge.data.models.SessionResponse
import com.lennydennis.mobilechallenge.data.remote.RetrofitAPIClient
import com.lennydennis.mobilechallenge.util.NetworkResult
import com.lennydennis.mobilechallenge.util.safeApiCall
import timber.log.Timber
import java.io.IOException

class SessionRepository {

    suspend fun getAllSessions() = safeApiCall(
        call = { getSessions() },
        errorMessage = "Something went wrong"
    )

    private suspend fun getSessions(): NetworkResult<SessionResponse> {
        val response = RetrofitAPIClient.apiService.getSessions()
        return when {
            response.isSuccessful -> {
                Timber.e("Fetched")
                NetworkResult.Success(response.body()!!)
            }
            else -> {
                Timber.e( "Not fetched")
                NetworkResult.Error(IOException("Something went wrong"))
            }
        }
    }
}