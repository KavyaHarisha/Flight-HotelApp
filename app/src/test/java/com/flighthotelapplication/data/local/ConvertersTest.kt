package com.flighthotelapplication.data.local

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ConvertersTest {
    lateinit var converters: Converters
    @Before
    fun setUp(){
        converters = mockk()
    }

    @Test
    fun listToJson() {
        every { converters.listToJson(listOf("oneImage","secondImage")) } returns "{list of images}"
        converters.listToJson(listOf("oneImage","secondImage"))
        verify { converters.listToJson(listOf("oneImage","secondImage")) }
    }

    @Test
    fun jsonToList() {
        every { converters.jsonToList("{convert to Json}") } returns listOf("convert","to","Json")
        converters.jsonToList("{convert to Json}")
        verify { converters.jsonToList("{convert to Json}") }
    }
}