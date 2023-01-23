package org.example.core

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import java.util.*

class StationTests {
    private val station = DecodedStation(UUID.fromString("1fccac1e-b47f-4f0e-9cce-5911b7bf01a1"), "RootStation")

    @Test
    fun testSerialisation() {
        val string: String = Json.encodeToString(station)
        assert(string == "{\"id\":\"1fccac1e-b47f-4f0e-9cce-5911b7bf01a1\",\"name\":\"RootStation\"}")
    }

    @Test
    fun testDeserialisation() {
        val stationFromJson = Json.decodeFromString<DecodedStation>("{\"id\":\"1fccac1e-b47f-4f0e-9cce-5911b7bf01a1\",\"name\":\"RootStation\"}")
        assert(stationFromJson == station)
    }
}