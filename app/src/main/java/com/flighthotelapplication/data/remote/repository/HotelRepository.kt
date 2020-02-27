package com.flighthotelapplication.data.remote.repository

import androidx.lifecycle.LiveData
import com.flighthotelapplication.data.local.dao.HotelDao
import com.flighthotelapplication.data.local.entity.HotelEntity
import com.flighthotelapplication.data.remote.ApiService
import com.flighthotelapplication.data.remote.NetworkBoundResource
import com.flighthotelapplication.data.remote.Resource
import retrofit2.Call
import javax.inject.Inject

class HotelRepository @Inject constructor(private var hotelDao: HotelDao,
                                          private var apiService: ApiService) {
    fun loadHotels():LiveData<Resource<HotelEntity>>{
        return object : NetworkBoundResource<HotelEntity,HotelEntity>(){
            override fun saveCallResult(item: HotelEntity?) {
                if(item!=null)hotelDao.saveHotelsList(item)
            }

            override fun loadFromDb(): LiveData<HotelEntity> {
                return hotelDao.loadHotels()
            }

            override fun createCall(): Call<HotelEntity> {
                return apiService.loadHotels()
            }

        }.asLiveData
    }
}