package com.flighthotelapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.flighthotelapplication.view.base.BaseActivity
import com.flighthotelapplication.databinding.ActivityMainBinding
import com.flighthotelapplication.utils.FragmentUtils
import com.flighthotelapplication.utils.FragmentUtils.TRANSITION_FADE_IN_OUT
import com.flighthotelapplication.view.fragment.FlightListFragment
import com.flighthotelapplication.view.fragment.HotelsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(){

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val bottomNavigationItemListener = BottomNavigationView.OnNavigationItemSelectedListener (){
        when (it.itemId) {
            R.id.navigation_flight -> {
                FragmentUtils.replaceFragment(
                    this,
                    FlightListFragment(),
                    R.id.frame_container,
                    false,
                    TRANSITION_FADE_IN_OUT
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hotel -> {
                FragmentUtils.replaceFragment(
                    this,
                    HotelsFragment(),
                    R.id.frame_container,
                    false,
                    TRANSITION_FADE_IN_OUT
                )
                return@OnNavigationItemSelectedListener true
            }
        }

        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentUtils.replaceFragment(
            this,
            FlightListFragment(),
            R.id.frame_container,
            false,
            TRANSITION_FADE_IN_OUT
        )
        dataBinding.navigation.setOnNavigationItemSelectedListener(bottomNavigationItemListener)
    }
}
