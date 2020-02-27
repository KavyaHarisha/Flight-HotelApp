package com.flighthotelapplication.data.local.entity

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FlightsData")
class FlightEntity {

    @SerializedName("airline")
    @Expose
    private val airline: String? = null
    @SerializedName("departure_date")
    @Expose
    private val departureDate: String? = null
    @SerializedName("arrival_date")
    @Expose
    private val arrivalDate: String? = null
    @SerializedName("price")
    @Expose
    private val price: Int? = null
    @SerializedName("departure_airport")
    @Expose
    private val departureAirport: String? = null
    @SerializedName("arrival_airport")
    @Expose
    private val arrivalAirport: String? = null
}