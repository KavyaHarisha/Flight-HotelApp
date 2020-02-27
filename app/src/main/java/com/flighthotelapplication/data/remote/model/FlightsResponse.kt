package com.flighthotelapplication.data.remote.model

import com.flighthotelapplication.data.local.entity.FlightEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FlightsResponse {
    @SerializedName("flights")
    @Expose
    var flights: List<FlightEntity>? = null
}