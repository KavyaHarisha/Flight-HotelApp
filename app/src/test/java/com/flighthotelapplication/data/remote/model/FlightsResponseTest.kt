package com.flighthotelapplication.data.remote.model

import com.flighthotelapplication.data.local.entity.FlightEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class FlightsResponseTest {

    @Test
    fun setFlights() {
        val flightsResponse = mockk<FlightsResponse>()
        val flight1 = mockk<FlightEntity>()
        val flight2 = mockk<FlightEntity>()
        every { flightsResponse.flights } returns listOf(flight1,flight2)
        flightsResponse.flights
        verify { flightsResponse.flights }
    }
}