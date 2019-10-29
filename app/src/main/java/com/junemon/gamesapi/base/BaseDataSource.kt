package com.junemon.gamesapi.base

import com.junemon.gamesapi.data.ResultToConsume
import com.junemon.gamesapi.util.timberLogE
import retrofit2.Response
/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultToConsume<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultToConsume.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResultToConsume<T> {
        timberLogE(message)
        return ResultToConsume.error("Network call has failed for a following reason: $message")
    }

}