package org.example.algorithms

import org.example.core.DecodedStation
import org.example.core.SearchMap
import org.example.core.exception.NodeNotFoundException
import java.util.*
import kotlin.collections.ArrayList

class BreadthFirstSearch: NodeSearch {
    override fun findNode(finalStation: DecodedStation, searchMap: SearchMap): DecodedStation {
        val rootNode = searchMap.rootNode
        // check and return if first node == final node
        if (rootNode == finalStation) return rootNode
        // queue to hold nodes to visit
        val nodesToVisit: Queue<DecodedStation> = LinkedList()
        val visitedNodes: ArrayList<DecodedStation> = arrayListOf()
        nodesToVisit.add(rootNode)
        visitedNodes.add(rootNode)
        rootNode.visited = true
        // start searching
        while(!nodesToVisit.isEmpty()) {
            val currentNode = nodesToVisit.remove()
            // check if node is targeted
            if (currentNode == finalStation) {
                return currentNode
            } else {
                // Check if it has child nodes or not.
                // If it has, start the searching process again.
                if (currentNode.children.isNotEmpty()) {
                    nodesToVisit.addAll(currentNode.children)
                }
            }
        }
        // no nodes left
        throw NodeNotFoundException()
    }
}