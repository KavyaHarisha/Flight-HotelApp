package com.flighthotelapplication.data.local.entity

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class HotelEntityTest {
    lateinit var hotelEntity: HotelEntity

    @Before
    fun setUp(){
        hotelEntity = mockk()
    }

    @Test
    fun setName() {
        every { hotelEntity.name} returns "Hotel"
        assertEquals(hotelEntity.name, "Hotel")
        verify { hotelEntity.name }
    }

    @Test
    fun setHotelLocation() {
        every { hotelEntity.hotelLocation} returns "Hotel location"
        assertEquals(hotelEntity.hotelLocation, "Hotel location")
        verify { hotelEntity.hotelLocation }
    }

    @Test
    fun setDescription() {
        every { hotelEntity.description} returns "Hotel description"
        assertEquals(hotelEntity.description, "Hotel description")
        verify { hotelEntity.description }
    }

    @Test
    fun setImages() {
        every { hotelEntity.images} returns listOf("oneImage","secondImage")
        assertEquals(hotelEntity.images, listOf("oneImage","secondImage"))
        verify { hotelEntity.images }
    }

    @Test
    fun setRating() {
        every { hotelEntity.rating} returns 4
        assertEquals(hotelEntity.rating, 4)
        verify { hotelEntity.rating }
    }

    @Test
    fun setFacilities() {
        every { hotelEntity.facilities} returns listOf("Beach,Help desk")
        assertEquals(hotelEntity.facilities, listOf("Beach,Help desk"))
        verify { hotelEntity.facilities }
    }
}