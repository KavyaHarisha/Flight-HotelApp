package com.flighthotelapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.flighthotelapplication.data.remote.repository.HotelRepository
import javax.inject.Inject

class HotelListViewModel @Inject constructor(hotelRepository: HotelRepository):ViewModel() {
    val hotels = hotelRepository.loadHotels()
}