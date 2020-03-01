package com.flighthotelapplication.di.builder

import com.flighthotelapplication.view.fragment.FlightListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeFlightListFragment(): FlightListFragment
}