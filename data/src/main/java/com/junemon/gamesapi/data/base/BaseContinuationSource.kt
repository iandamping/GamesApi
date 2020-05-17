package com.junemon.gamesapi.data.base

import com.ian.app.helper.data.ResultToConsume
import com.junemon.model.RepositoryDataHelper
import com.junemon.model.RemoteDataSourceHelper
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


    protected suspend fun <T> Call<T>.doOneShot(): RepositoryDataHelper<T> =
        suspendCancellableCoroutine { cancellableContinuation ->
            this.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    cancellableContinuation.resume(RepositoryDataHelper.Error(exception = Exception(t),cache = null))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    try {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) cancellableContinuation.resume(
                                RepositoryDataHelper.Success(
                                    body
                                )
                            )
                        }
                    } catch (e: Exception) {
                        cancellableContinuation.resume(RepositoryDataHelper.Error(exception = e,cache = null))
                    }
                }
            })
            cancellableContinuation.invokeOnCancellation {
                this.cancel()
            }
        }
/*
    protected fun <T> results() = CompletableDeferred<T>()*/

    protected suspend fun <T> Response<T>.getResult(): ResultToConsume<T>
        = suspendCancellableCoroutine { cancellableContinuation ->
            if (this.isSuccessful) {
                val body = this.body()
                if (body != null) cancellableContinuation.resume(ResultToConsume.success(body))
            }else cancellableContinuation.resume(error(" ${this.code()} ${this.message()}"))

            cancellableContinuation.invokeOnCancellation {
                cancellableContinuation.resume(error(it?.message ?: it.toString()))
            }
        }

    protected suspend fun <T> Call<T>.doOneShots(): RemoteDataSourceHelper<T> =
        suspendCancellableCoroutine { cancellableContinuation ->
            this.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    cancellableContinuation.resume(RemoteDataSourceHelper.Error(exception = Exception(t)))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    try {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) cancellableContinuation.resume(
                                RemoteDataSourceHelper.Success(
                                    body
                                )
                            )
                        }
                    } catch (e: Exception) {
                        cancellableContinuation.resume(RemoteDataSourceHelper.Error(exception = e))
                    }
                }
            })
            cancellableContinuation.invokeOnCancellation {
                this.cancel()
            }
        }


    private fun <T> error(message: String): ResultToConsume<T> {
        return ResultToConsume.error("Network call has failed for a following reason: $message")
    }
}



