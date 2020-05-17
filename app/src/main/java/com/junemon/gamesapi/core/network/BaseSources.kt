package com.junemon.gamesapi.core.network

import com.junemon.gamesapi.core.model.Results
import retrofit2.Response

abstract class BaseSources {

    inline fun <T> get(call: () -> Response<T>): Results<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Results.Success(
                    body
                )
            }
            return Results.Error(Exception())
        } catch (e: Exception) {
            return Results.Error(e)
        }
    }
}
