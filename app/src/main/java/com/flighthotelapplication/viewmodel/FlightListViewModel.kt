package com.flighthotelapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flighthotelapplication.data.local.entity.FlightEntity
import com.flighthotelapplication.data.remote.Resource
import com.flighthotelapplication.data.remote.repository.FlightRepository
import javax.inject.Inject

class FlightListViewModel @Inject constructor(flightRepository: FlightRepository):ViewModel() {
    val flights:LiveData<Resource<List<FlightEntity>>> = flightRepository.loadFlights()
}