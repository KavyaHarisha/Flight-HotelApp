package com.flighthotelapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.flighthotelapplication.data.local.entity.HotelEntity

@Dao
interface HotelDao {
    @Query("SELECT * FROM HotelData")
    fun loadHotels():LiveData<HotelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHotelsList(hotelList: HotelEntity)

    @Transaction
    @Query("DELETE FROM HotelData")
    fun deleteAllData()
}