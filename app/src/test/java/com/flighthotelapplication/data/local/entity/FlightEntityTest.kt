package com.flighthotelapplication.data.local.entity

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class FlightEntityTest {

    lateinit var flightEntity:FlightEntity

    @Before
    fun setUp(){
        flightEntity = mockk()
    }

    @Test
    fun setId() {
        every { flightEntity.id} returns 2
        assertEquals(flightEntity.id, 2)
        verify { flightEntity.id }
    }

    @Test
    fun setAirline() {
        every { flightEntity.airline} returns "airline"
        assertEquals(flightEntity.airline, "airline")
        verify { flightEntity.airline }
    }

    @Test
    fun setDepartureDate() {
        every { flightEntity.departureDate} returns "10-04-2020T10:20:00Z"
        assertEquals(flightEntity.departureDate, "10-04-2020T10:20:00Z")
        verify { flightEntity.departureDate }
    }

    @Test
    fun setArrivalDate() {
        every { flightEntity.arrivalDate} returns "10-04-2020T12:20:00Z"
        assertEquals(flightEntity.arrivalDate, "10-04-2020T12:20:00Z")
        verify { flightEntity.arrivalDate }
    }

    @Test
    fun setPrice() {
        every { flightEntity.price} returns 20000
        assertEquals(flightEntity.price, 20000)
        verify { flightEntity.price }
    }

    @Test
    fun setDepartureAirport() {
        every { flightEntity.departureAirport} returns "India"
        assertEquals(flightEntity.departureAirport, "India")
        verify { flightEntity.departureAirport }
    }

    @Test
    fun setArrivalAirport() {
        every { flightEntity.arrivalAirport} returns "UK"
        assertEquals(flightEntity.arrivalAirport, "UK")
        verify { flightEntity.arrivalAirport }
    }
}