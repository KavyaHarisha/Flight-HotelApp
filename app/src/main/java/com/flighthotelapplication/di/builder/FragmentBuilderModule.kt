package com.flighthotelapplication.di.builder

import com.flighthotelapplication.view.fragment.FlightListFragment
import com.flighthotelapplication.view.fragment.HotelsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeFlightListFragment(): FlightListFragment

    @ContributesAndroidInjector
    abstract fun contributeHotelFragment(): HotelsFragment
}