package com.flighthotelapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flighthotelapplication.data.local.entity.HotelEntity

interface HotelDao {
    @Query("SELECT * FROM HotelData")
    fun loadHotels():LiveData<HotelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHotelsList(hotelList: HotelEntity)
}