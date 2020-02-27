package com.flighthotelapplication.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.flighthotelapplication.data.local.FlightOrHotelDatabase
import com.flighthotelapplication.data.local.dao.FlightDao
import com.flighthotelapplication.data.local.dao.HotelDao
import com.flighthotelapplication.data.remote.*


import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor(RequestInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideFlightOrHotelDatabase(application: Application): FlightOrHotelDatabase {
        return Room.databaseBuilder(application, FlightOrHotelDatabase::class.java, "flightOrHotel.db").build()
    }

    @Provides
    @Singleton
    internal fun provideFlightDao(flightOrHotelDatabase: FlightOrHotelDatabase): FlightDao {
        return flightOrHotelDatabase.flightDao()
    }

    @Provides
    @Singleton
    internal fun provideHotelDao(flightOrHotelDatabase: FlightOrHotelDatabase): HotelDao {
        return flightOrHotelDatabase.hotelDao()
    }

}
