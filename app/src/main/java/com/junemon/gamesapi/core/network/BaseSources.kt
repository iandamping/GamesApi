package com.junemon.gamesapi.core.network

import com.junemon.model.Results
import retrofit2.Response

abstract class BaseSources {

    inline fun <T> oneShotCalls(call: () -> Response<T>): Results<T> {
        try {
            val response = call()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Results.Success(body)
                }else{
                    Results.Error(Exception("body is null"))
                }
            } else return Results.Error(Exception("response not success"))
        } catch (e: Exception) {
            return Results.Error(e)
        }
    }
}
