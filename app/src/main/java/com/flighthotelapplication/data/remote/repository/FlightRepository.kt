package com.flighthotelapplication.data.remote.repository

import androidx.lifecycle.LiveData
import com.flighthotelapplication.data.local.dao.FlightDao
import com.flighthotelapplication.data.local.entity.FlightEntity
import com.flighthotelapplication.data.remote.ApiService
import com.flighthotelapplication.data.remote.NetworkBoundResource
import com.flighthotelapplication.data.remote.Resource
import com.flighthotelapplication.data.remote.model.FlightsResponse
import retrofit2.Call
import javax.inject.Inject

class FlightRepository @Inject constructor(private var flightDao:FlightDao,
                                           private var apiService:ApiService) {

    fun loadFlights():LiveData<Resource<List<FlightEntity>>>{
        return object : NetworkBoundResource<List<FlightEntity>,FlightsResponse>(){
            override fun saveCallResult(item: FlightsResponse?) {
                flightDao.deleteAllData()
                 if (null != item) flightDao.saveFlightsList(item.flights)
            }

            override fun loadFromDb(): LiveData<List<FlightEntity>> {
                return flightDao.loadFlights()
            }

            override fun createCall(): Call<FlightsResponse> {
                return apiService.loadFlights()
            }

        }.asLiveData
    }
}