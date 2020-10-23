package com.junemon.gamesapi.core.network

import com.junemon.model.Results
import retrofit2.Response
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
