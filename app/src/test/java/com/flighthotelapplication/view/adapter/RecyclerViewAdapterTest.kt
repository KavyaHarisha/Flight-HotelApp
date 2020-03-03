package com.flighthotelapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import com.flighthotelapplication.data.local.entity.FlightEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import org.junit.Test

import org.junit.Assert.*

class RecyclerViewAdapterTest {
    var flightList = ObservableArrayList<FlightEntity>()
    var itemLayout = 0
    var recyclerViewAdapterTest = RecyclerViewAdapter(flightList,itemLayout)
    var context = mockk<Context>()

    @Test
    fun onCreateViewHolder() {

       mockkStatic(LayoutInflater::class)
        val layoutInflater = mockk<LayoutInflater>()
        val viewGropu = mockk<ViewGroup>()
        every { viewGropu.context } returns context
        every { LayoutInflater.from(context) } returns layoutInflater

        recyclerViewAdapterTest.onCreateViewHolder(viewGropu,0)
    }

    @Test
    fun getItemCount() {
    }

    @Test
    fun onBindViewHolder() {
    }

    @Test
    fun getList() {
    }
}