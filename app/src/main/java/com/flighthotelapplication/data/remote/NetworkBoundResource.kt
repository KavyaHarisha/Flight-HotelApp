package com.flighthotelapplication.data.remote


import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
            "time our error"
            //context.getString(R.string.requestTimeOutError)
        } else if (error is MalformedJsonException) {
            "mal formed json"
            //context.getString(R.string.responseMalformedJson)
        } else if (error is IOException) {
            "newtwork error"
            //context.getString(R.string.networkError)
        } else if (error is HttpException) {
            error.response().message()
        } else {
            "unknown error"
            //context.getString(R.string.unknownError)
        }

    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private fun saveResultAndReInit(response: V?) {
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                result.addSource(loadFromDb()) { newData ->
                    if (null != newData)
                        result.value = Resource.success(newData)
                }
            }
        }.execute()
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
