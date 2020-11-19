package com.junemon.gamesapi.core.data.datasource.remote.network

import com.junemon.gamesapi.core.domain.model.Results
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseSources {

    inline fun <T> oneShotCalls(call: () -> Response<T>): Results<T> {
        try {
            val response = call.invoke()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Results.Success(body)
                }else{
                    Results.Error(Exception("body is null"))
                }
            } else return Results.Error(Exception("response not success"))
        } catch (e: Exception) {
            return when(e){
                is IOException ->{
                    Results.Error(e)
                }
                is SocketTimeoutException ->{
                    Results.Error(e)
                }
                else ->{
                    Results.Error(e)
                }
            }
        }
    }
}
