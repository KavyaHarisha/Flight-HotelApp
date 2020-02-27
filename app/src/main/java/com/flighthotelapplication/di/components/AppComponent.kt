package com.flighthotelapplication.di.components

import android.app.Application
import com.flighthotelapplication.FlightHotelApp
import com.flighthotelapplication.di.builder.ActivityBuilderModule
import com.flighthotelapplication.di.module.AppModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(flightHotelApp: FlightHotelApp)
}