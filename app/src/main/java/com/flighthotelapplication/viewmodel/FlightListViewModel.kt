package com.flighthotelapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.flighthotelapplication.data.remote.repository.FlightRepository
import javax.inject.Inject

class FlightListViewModel @Inject constructor(flightRepository: FlightRepository):ViewModel() {
    val flights = flightRepository.loadFlights()
}