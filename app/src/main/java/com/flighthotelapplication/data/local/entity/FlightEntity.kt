package com.flighthotelapplication.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FlightsData")
open class FlightEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "airline")
    @SerializedName("airline")
    var airline: String? = null

    @ColumnInfo(name = "departure_date")
    @SerializedName("departure_date")
    var departureDate: String? = null

    @ColumnInfo(name = "arrival_date")
    @SerializedName("arrival_date")
    var arrivalDate: String? = null

    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price: Int? = null

    @ColumnInfo(name = "departure_airport")
    @SerializedName("departure_airport")
    var departureAirport: String? = null

    @ColumnInfo(name = "arrival_airport")
    @SerializedName("arrival_airport")
    var arrivalAirport: String? = null
}