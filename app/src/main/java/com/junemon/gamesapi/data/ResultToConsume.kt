package com.junemon.gamesapi.data


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class ResultToConsume<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultToConsume<T> {
            return ResultToConsume(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultToConsume<T> {
            return ResultToConsume(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultToConsume<T> {
            return ResultToConsume(Status.LOADING, data, null)
        }
    }
}