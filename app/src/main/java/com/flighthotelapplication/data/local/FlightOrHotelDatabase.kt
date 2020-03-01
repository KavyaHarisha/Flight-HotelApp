package com.flighthotelapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.flighthotelapplication.data.local.dao.FlightDao
import com.flighthotelapplication.data.local.dao.HotelDao
import com.flighthotelapplication.data.local.entity.FlightEntity
import com.flighthotelapplication.data.local.entity.HotelEntity

@Database(entities = [FlightEntity::class,HotelEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class FlightOrHotelDatabase: RoomDatabase() {
    abstract fun flightDao():FlightDao
    abstract fun hotelDao():HotelDao
}