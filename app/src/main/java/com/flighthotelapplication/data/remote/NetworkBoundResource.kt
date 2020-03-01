package com.flighthotelapplication.data.remote


import android.annotation.SuppressLint
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.flighthotelapplication.utils.ThreadUtils
import com.google.gson.stream.MalformedJsonException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


abstract class NetworkBoundResource<T, V>
@MainThread
protected constructor() {
    private val result = MediatorLiveData<Resource<T>>()

    val asLiveData: LiveData<Resource<T>>
        get() = result

    init {

        result.value = Resource.loading(null)
        val dbSource = loadFromDb()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch()) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    if (null != newData)
                        result.value = Resource.success(newData)
                }
            }
        }
    }


    private fun fetchFromNetwork(dbSource: LiveData<T>) {
        result.addSource(dbSource) { newData -> result.setValue(Resource.loading(newData)) }
        createCall().enqueue(object : Callback<V> {
            override fun onResponse(@NonNull call: Call<V>, @NonNull response: Response<V>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body())
            }

            override fun onFailure(@NonNull call: Call<V>, @NonNull t: Throwable) {
                result.removeSource(dbSource)
                result.addSource(
                    dbSource
                ) { newData ->
                    result.setValue(
                        Resource.error(
                            getCustomErrorMessage(t)
                        )
                    )
                }
            }
        })
    }

    private fun getCustomErrorMessage(error: Throwable): String {

        return if (error is SocketTimeoutException) {
            "Time out error"
        } else if (error is MalformedJsonException) {
            "Mal formed json"
        } else if (error is IOException) {
            "Network error"
        } else if (error is HttpException) {
            error.response().message()
        } else {
            "Unknown error"
        }

    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private fun saveResultAndReInit(response: V?) {
        ThreadUtils.runOnBackgroundThread(Runnable {
            saveCallResult(response)
            ThreadUtils.runOnMainThread(Runnable {
                result.addSource(loadFromDb()) { newData ->
                    if (null != newData)
                        result.value = Resource.success(newData)
                }
            })
        })
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: V?)

    @MainThread
    private fun shouldFetch(): Boolean {
        return true
    }

    @NonNull
    @MainThread
    protected abstract fun loadFromDb(): LiveData<T>

    @NonNull
    @MainThread
    protected abstract fun createCall(): Call<V>
}
