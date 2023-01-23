package org.example.algorithms

import org.example.core.DecodedStation
import org.example.core.ResultStation
import org.example.core.SearchMap
import java.util.LinkedList

interface NodeSearch {
    fun findNode(finalStation: DecodedStation, searchMap: SearchMap): LinkedList<ResultStation>
}