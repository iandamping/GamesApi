package com.junemon.gamesapi.data.base

import com.ian.app.helper.data.ResultToConsume
import com.ian.app.helper.util.timberLogE
import com.ian.app.helper.util.timberLogI
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

/**
 * Created by Ian Damping on 06,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseDataSources {

    /*protected suspend fun <T> Call<T>.doOneShot(): ResultToConsume<T> =
        suspendCancellableCoroutine { cancellableContinuation ->
            this.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    cancellableContinuation.resume(ResultToConsume.error(t.message!!))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    try {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) cancellableContinuation.resume(
                                ResultToConsume.success(
                                    body
                                )
                            )
                        }
                    } catch (e: Exception) {
                        cancellableContinuation.resume(ResultToConsume.error(e.message!!))
                    }
                }
            })
            cancellableContinuation.invokeOnCancellation {
                this.cancel()
            }
        }*/



    protected suspend fun <T> Response<T>.getResult(): ResultToConsume<T> {
        return suspendCancellableCoroutine { cancellableContinuation ->
            if (this.isSuccessful) {
                val body = this.body()
                if (body != null) cancellableContinuation.resume(ResultToConsume.success(body))
            }else cancellableContinuation.resume(ResultToConsume.error(this.message()))

            cancellableContinuation.invokeOnCancellation {
                cancellableContinuation.resume(ResultToConsume.error(it?.message!!))
            }
        }
    }
}



