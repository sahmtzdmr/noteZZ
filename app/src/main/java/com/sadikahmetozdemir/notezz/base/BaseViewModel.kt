package com.sadikahmetozdemir.notezz.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.sadikahmetozdemir.notezz.core.ui.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val baseEvent = SingleLiveEvent<BaseViewEvent>()

    fun navigate(directions: NavDirections) = viewModelScope.launch {
        baseEvent.postValue(BaseViewEvent.NavigateTo(directions))
    }

    fun showMessage(message: String) = viewModelScope.launch {
        if (message.isBlank())
            return@launch
        baseEvent.postValue(BaseViewEvent.ShowMessage(message))
    }

    fun backTo() {
        baseEvent.postValue(BaseViewEvent.NavigateBack)
    }

    fun showToast(message: String) = viewModelScope.launch {
        if (message.isBlank())
            return@launch
        baseEvent.postValue(BaseViewEvent.ShowToast(message))
    }

    fun handleException(exception: Exception) {
        when (exception) {
//            is SimpleHttpException -> {
//                exception.friendlyMessage?.let { showMessage(it) }
//            }
            else -> {
                exception.message?.let { showMessage(it) }
            }
        }
    }

    fun <T : Any?> sendRequest(
        request: suspend () -> T,
        success: ((T) -> Unit)? = null,
        error: ((Exception) -> Unit)? = null,
        complete: (() -> Unit)? = null,
    ): Job {
        return viewModelScope.launch {
            try {
                val result = request.invoke()
                success?.invoke(result)
            } catch (exception: Exception) {
                if (error != null) {
                    error.invoke(exception)
                } else {
                    handleException(exception)
                }
            }

            complete?.invoke()
        }
    }

    fun setExtras(key: String, value: Any) {
        baseEvent.value = BaseViewEvent.Extras(key, value)
    }
}
