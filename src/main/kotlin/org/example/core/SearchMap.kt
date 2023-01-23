package org.example.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*

/// Class representing the search space
/// Assume there are no circular dependencies
class SearchMap (val rootNode: DecodedStation){
    companion object {
        fun create(path: String): SearchMap {
            // load the json and transform into search node collection
            val decodedStations = Json.decodeFromString<DecodedStation>(path)
            return SearchMap(decodedStations)
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other is SearchMap) {
           return other.rootNode == rootNode
        }
        return false
    }
}

@Serializable
data class DecodedStation(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val children: List<DecodedStation> = listOf(),
    var visited: Boolean = false
)

data class ResultStation(
    val id: UUID,
    val name: String,
    val child: ResultStation?
)