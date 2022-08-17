package com.mikhailapps.architecture.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mikhailapps.architecture.domain.Resource
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseDataDrivenFragment : Fragment() {

    open val defaultFetchInOnStart = true
    open val defaultForceFetch = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        if (defaultFetchInOnStart) {
            fetchData(defaultForceFetch)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!defaultFetchInOnStart) {
            fetchData(defaultForceFetch)
        }
    }

    abstract fun observeData()
    abstract fun fetchData(force: Boolean)
    abstract fun setUi()


    protected fun <T> StateFlow<Resource<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((Resource<T>) -> Unit)? = null,
        onError: ((error: Throwable) -> Unit),
        onSuccess: ((data: T?) -> Unit),
        onProgress: () -> Unit
    ) {
        collectFlowSafely(lifecycleState) {
            this.collect {
                state?.invoke(it)
                when (it) {
                    is Resource.Idle -> {}
                    is Resource.Progress -> { onProgress.invoke()}
                    is Resource.Failure -> onError.invoke(it.throwable)
                    is Resource.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    private fun collectFlowSafely(
        lifecycleState: Lifecycle.State,
        collect: suspend () -> Unit
    ) {
        viewLifecycleOwner.run {
            lifecycleScope.launch {
                repeatOnLifecycle(lifecycleState) {
                    collect()
                }
            }
        }
    }


}