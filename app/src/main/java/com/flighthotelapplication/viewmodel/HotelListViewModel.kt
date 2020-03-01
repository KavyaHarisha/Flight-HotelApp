package com.flighthotelapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.flighthotelapplication.data.local.entity.HotelEntity
import com.flighthotelapplication.data.remote.Resource
import com.flighthotelapplication.data.remote.repository.HotelRepository
import javax.inject.Inject

class HotelListViewModel @Inject constructor(hotelRepository: HotelRepository):ViewModel() {
    val hotels:LiveData<Resource<HotelEntity>> = hotelRepository.loadHotels()
}