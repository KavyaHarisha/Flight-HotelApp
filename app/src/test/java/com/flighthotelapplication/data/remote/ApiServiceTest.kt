package com.flighthotelapplication.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceTest {
    lateinit var apiService: ApiService

    @Before
    fun createService() {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor(RequestInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun loadFlights() {
        try {
            val response: Response<*> = apiService.loadFlights().execute()
            assertEquals(response.code(), 200)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun loadHotel() {
        try {
            val response: Response<*> = apiService.loadHotels().execute()
            assertEquals(response.code(), 200)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}