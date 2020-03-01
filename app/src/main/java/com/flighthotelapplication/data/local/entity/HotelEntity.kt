package com.flighthotelapplication.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "HotelData")
class HotelEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "hotel_location")
    @SerializedName("hotel_location")
    var hotelLocation: String? = null

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null

    @ColumnInfo(name = "images")
    @SerializedName("images")
    var images: List<String>? = null

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    var rating: Int? = null

    @ColumnInfo(name = "facilities")
    @SerializedName("facilities")
    var facilities: List<String>? = null
}