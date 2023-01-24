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
            decodedStations.setChildren()
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
) {
    private var parent: DecodedStation? = null
    fun setChildren() {
        for(station in children) {
            station.parent = this
        }

        for(station in children) {
            station.setChildren()
        }
    }

    override fun toString(): String {
        return if (parent != null) {
            val builder = StringBuilder()
            builder.append(parent.toString())
            builder.append(" node values: id $id, name: $name")
            builder.toString()
        } else {
            " node values: id $id, name: $name"
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other is DecodedStation) {
            if(other.id == id) {
                if(other.children.size == children.size) {
                    return other.children.toTypedArray() contentDeepEquals children.toTypedArray()
                }
            }
        }
        return false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}