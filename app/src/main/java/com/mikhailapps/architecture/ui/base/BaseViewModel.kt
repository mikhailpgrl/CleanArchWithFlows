package com.mikhailapps.architecture.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailapps.architecture.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T> MutableStateFlow() = MutableStateFlow<UiState<T>>(UiState.idle())

    protected fun <T> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<UiState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UiState.loading()
            this@collectRequest.collect { resource ->
                state.value = resource.asUiState()
            }
        }
    }

    protected fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<UiState<S>>,
        mappedData: (T?) -> S?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UiState.loading()
            this@collectRequest.collect { resource ->
                state.value = resource.asUiState(mappedData)
            }
        }
    }
}