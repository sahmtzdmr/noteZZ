package com.sadikahmetozdemir.notezz.data.repository

abstract class BaseRepository() {
    suspend fun <T> execute(request: suspend () -> T): T {
        return try {
            request.invoke()
        } catch (exception: Exception) {
            throw (exception)
        }
    }

    open suspend fun <T> fetchFromLocal(cached: suspend () -> T?): T? {
        return try {
            cached.invoke()
        } catch (ex: Exception) {
            throw (ex)
        }
    }


}