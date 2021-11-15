package com.lennydennis.mobilechallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lennydennis.mobilechallenge.data.models.SessionResponse
import com.lennydennis.mobilechallenge.repository.SessionRepository
import com.lennydennis.mobilechallenge.util.NetworkResult
import kotlinx.coroutines.launch

class SessionViewModel(application: Application) : AndroidViewModel(application){

    private val sessionRepository = SessionRepository()
    val sessionResponse = MutableLiveData<NetworkResult<SessionResponse>>()

    fun getSessions() {
        viewModelScope.launch {
            sessionResponse.postValue(sessionRepository.getAllSessions())
        }
    }
}