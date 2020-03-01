package com.flighthotelapplication.di.module

import androidx.lifecycle.ViewModel
import com.flighthotelapplication.viewmodel.FlightListViewModel
import com.flighthotelapplication.viewmodel.HotelListViewModel
import com.flighthotelapplication.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FlightListViewModel::class)
    internal abstract fun bindsFlightListViewModel(flightListViewModel: FlightListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HotelListViewModel::class)
    internal abstract fun bindsHotelListViewModel(hotelListViewModel: HotelListViewModel): ViewModel
}