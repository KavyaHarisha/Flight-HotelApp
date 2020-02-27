package com.flighthotelapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flighthotelapplication.data.local.dao.FlightDao
import com.flighthotelapplication.data.local.dao.HotelDao
import com.flighthotelapplication.data.local.entity.FlightEntity
import com.flighthotelapplication.data.local.entity.HotelEntity

@Database(entities = [FlightEntity::class,HotelEntity::class], version = 2)
abstract class FlightOrHotelDatabase: RoomDatabase() {
    abstract fun flightDao():FlightDao
    abstract fun hotelDao():HotelDao
}