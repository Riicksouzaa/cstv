package com.codenome.cstv.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenome.cstv.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(private val repository: BaseRepository<T>) : ViewModel() {

    private val _flowState = MutableStateFlow<Resource<T>>(Resource.loading())
    open val flowState
        get() = _flowState

    open fun refresh() {
        viewModelScope.launch {
            repository.result.collect {
                _flowState.value = it
            }
        }
    }
}