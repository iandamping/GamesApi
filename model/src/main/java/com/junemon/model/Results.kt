package com.junemon.model

sealed class Results<out R> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()
}

sealed class DataHelper<out T> {
    data class RemoteSourceValue<out T>(val data: T) : DataHelper<T>()
    data class RemoteSourceError(val exception: Exception) : DataHelper<Nothing>()
}

sealed class ConsumeResult<out T> {
    data class ConsumeData<out T>(val data: List<T>) : ConsumeResult<T>()
    data class ConsumeSingleData<out T>(val data: T) : ConsumeResult<T>()
    data class ErrorHappen(val exception: Exception) : ConsumeResult<Nothing>()
    object Loading : ConsumeResult<Nothing>()
    object Complete : ConsumeResult<Nothing>()
}

sealed class CachedDataHelper<out T> {
    object Loading : CachedDataHelper<Nothing>()
    data class RemoteSourceValue<out T>(val data: T) : CachedDataHelper<T>()
    data class RemoteSourceError(val exception: Exception) : CachedDataHelper<Nothing>()
    object Complete : CachedDataHelper<Nothing>()
}

sealed class ConsumeCacheResult<out T> {
    data class ConsumeData<out T>(val data: List<T>) : ConsumeCacheResult<T>()
    data class ErrorHappen(val exception: Exception) : ConsumeCacheResult<Nothing>()
    data class Loading <out T>(val cache: List<T>?) : ConsumeCacheResult<T>()
    object Complete : ConsumeCacheResult<Nothing>()
}
