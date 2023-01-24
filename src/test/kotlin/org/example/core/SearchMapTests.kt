package org.example.core

import org.fixtures.Fixtures
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class SearchMapTests {
    @Test
    fun testSearchMapCorrectlyCreatesMapFromSimpleJson() {
        val levelOneChildOne = DecodedStation(UUID.fromString("1fdcab1e-c76d-4f0e-3ddf-6917b7bf01a2"), "l2a")
        val levelOneChildTwo = DecodedStation(UUID.fromString("1fccac1e-b47f-4f0e-9cce-5911b7bf01a1"), "l2b")
        val rootStation = DecodedStation(UUID.fromString("7fdcab1e-c76d-4f0e-3ddf-6917b7bf01a2"), "root", listOf(levelOneChildOne, levelOneChildTwo))
        rootStation.setChildren()
        val map = SearchMap(rootStation)
        val testString = Fixtures.loadTestFileFromLocation("simpleSearchMap.json")
        val sut = SearchMap.create(testString)
        assertEquals(map,sut)
    }
}