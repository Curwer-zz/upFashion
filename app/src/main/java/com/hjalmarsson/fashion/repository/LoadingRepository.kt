package com.hjalmarsson.fashion.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class LoadingRepository @Inject constructor() {
    private val data: MutableSet<String> = mutableSetOf()
    val loadingState: LiveData<LoadingState> = MutableLiveData<LoadingState>()
    val dataError: MutableLiveData<Throwable?> = MutableLiveData()

    fun addLoader(tag: String) {
        if (!data.contains(tag)) {
            data.add(tag)
            refreshLoadingState()
        }
    }

    fun removeLoader(tag: String) {
        if (data.contains(tag)) {
            data.remove(tag)
            refreshLoadingState()
        }
    }

    private fun refreshLoadingState() = (loadingState as MutableLiveData).postValue(getLoadingState())

    private fun getLoadingState() = if (data.isEmpty()) LoadingState.Idle else LoadingState.Loading
}

enum class LoadingState {
    Idle,
    Loading
}