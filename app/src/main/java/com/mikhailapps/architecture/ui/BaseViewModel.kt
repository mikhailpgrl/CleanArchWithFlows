package com.mikhailapps.architecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailapps.architecture.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

open class BaseViewModel() : ViewModel() {

    protected fun <T> MutableStateFlow() = MutableStateFlow<Resource<T>>(Resource.idle())


    
    protected fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<Resource<T>>,
        mappedData: (T) -> S
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
                    else -> {}
                }
            }
        }
    }

}