package com.flighthotelapplication

import android.app.Activity
import android.app.Application
import com.flighthotelapplication.di.components.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class FlightHotelApp:Application(),HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    private fun initializeComponent(){
        DaggerAppComponent.builder().application(this)
            .build()
            .inject(this)
    }
}