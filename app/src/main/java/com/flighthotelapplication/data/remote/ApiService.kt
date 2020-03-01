package com.flighthotelapplication.data.remote

import com.flighthotelapplication.data.local.entity.HotelEntity
import com.flighthotelapplication.data.remote.model.FlightsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("bFnZQEx0")
    fun loadFlights(): Call<FlightsResponse>

    @GET("f0Tm6bfy")
    fun loadHotels(): Call<HotelEntity>
}