package com.lennydennis.mobilechallenge.data.remote

import com.lennydennis.mobilechallenge.data.models.SessionResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("sessions.json")
    suspend fun getSessions(): Response<SessionResponse>
}