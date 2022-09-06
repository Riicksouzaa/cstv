package com.codenome.cstv.base

import android.content.Context
import com.codenome.cstv.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository<T>(context: Context) {
    protected abstract suspend fun getFromApi(id: Int? = null): T?

    val result: Flow<Resource<T>> = flow {
        emit(Resource.loading())
        try {
            getFromApi()?.let {
                emit(Resource.success(it))
            } ?: kotlin.run { emit(Resource.error("Ocorreu um erro, tente novamente.")) }
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }
    }
}