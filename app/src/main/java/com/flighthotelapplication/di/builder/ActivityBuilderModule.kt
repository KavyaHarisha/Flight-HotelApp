package com.flighthotelapplication.di.builder


import com.flighthotelapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    internal abstract fun mainActivity(): MainActivity


}
