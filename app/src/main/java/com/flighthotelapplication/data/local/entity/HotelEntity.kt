package com.flighthotelapplication.data.local.entity

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



@Entity(tableName = "HotelData")
class HotelEntity {
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("hotel_location")
    @Expose
    private val hotelLocation: String? = null
    @SerializedName("description")
    @Expose
    private val description: String? = null
    @SerializedName("images")
    @Expose
    private val images: List<String>? = null
    @SerializedName("rating")
    @Expose
    private val rating: Int? = null
    @SerializedName("facilities")
    @Expose
    private val facilities: List<String>? = null
}