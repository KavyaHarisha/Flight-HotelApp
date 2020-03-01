package com.flighthotelapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.flighthotelapplication.data.local.entity.FlightEntity

@Dao
interface FlightDao {
    @Query("SELECT * FROM FlightsData")
    fun loadFlights(): LiveData<List<FlightEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFlightsList(flightList : List<FlightEntity>?)

    @Transaction
    @Query("DELETE FROM FlightsData")
    fun deleteAllData()
}