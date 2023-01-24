package org.example.algorithms

import org.example.core.DecodedStation
import org.example.core.SearchMap
import org.example.core.exception.NodeNotFoundException
import kotlin.jvm.Throws

interface NodeSearch {
    @Throws(NodeNotFoundException::class)
    fun findNode(finalStation: DecodedStation, searchMap: SearchMap): DecodedStation
}