package com.mikhailapps.architecture.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailapps.architecture.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel() : ViewModel() {

    protected fun <T> MutableStateFlow() = MutableStateFlow<Resource<T>>(Resource.idle())

    protected fun <T> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<Resource<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = Resource.loading()
            this@collectRequest.collect {
                when (it) {
                    is Resource.Success -> {
                        state.value = Resource.success(it.data)
                    }
                    is Resource.Failure -> {

                    }
                    is Resource.Idle -> {

                    }
                    is Resource.Progress -> {

                    }
                }
            }
        }
    }

    protected fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<Resource<S>>,
        mappedData: (T?) -> S?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = Resource.loading()
            this@collectRequest.collect {
                when (it) {
                    is Resource.Success -> {
                        state.value = Resource.success(mappedData(it.data))
                    }
                    is Resource.Failure -> {

                    }
                    is Resource.Idle -> {

                    }
                    is Resource.Progress -> {

                    }
                }
            }
        }
    }
}