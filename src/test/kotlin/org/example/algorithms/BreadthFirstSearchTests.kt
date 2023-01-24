package org.example.algorithms

import org.example.core.DecodedStation
import org.example.core.SearchMap
import org.example.core.exception.NodeNotFoundException
import org.fixtures.Fixtures
import org.junit.Test
import java.util.*
import kotlin.test.assertNotNull

class BreadthFirstSearchTests {

    private fun generateRandomStation(): DecodedStation {
        return DecodedStation(UUID.randomUUID(), "RandomStation")
    }
    @Test(expected = NodeNotFoundException::class)
    fun testCorrectExceptionThrownIfNoNodeFound() {
        val finalNode = generateRandomStation()
        val testString = Fixtures.loadTestFileFromLocation("twoLevelSearchMap.json")
        val searchMap = SearchMap.create(testString)
        val sut = BreadthFirstSearch()
        sut.findNode(finalNode, searchMap)
    }
    @Test
    fun testBreadthFirstSearchReturnsCorrectlyIfFirstNodeIsGoalNode() {
        val finalNode = DecodedStation(UUID.fromString("34a3db77-4a33-411b-be3d-4ae61dde1367"), "l2bl3a")
        val expectedResult = DecodedStation(UUID.fromString("34a3db77-4a33-411b-be3d-4ae61dde1367"), "l2bl3a")
        val searchMap = SearchMap(finalNode)
        val sut = BreadthFirstSearch()
        val result = sut.findNode(finalNode, searchMap)
        assert(result == expectedResult)
    }

    @Test
    fun testBreadthFirstSearchObtainsCorrectList() {
        // set up expected LinkedList result
        val nodeToFind = DecodedStation(UUID.fromString("34a3db77-4a33-411b-be3d-4ae61dde1367"), "l2bl3a")

        // so need to set the children and then call setChildren
        val l2bl3a = DecodedStation(
            UUID.fromString("34a3db77-4a33-411b-be3d-4ae61dde1367"),
            name = "l2bl3a")

        val l1Node = DecodedStation(
            UUID.fromString("1fccac1e-b47f-4f0e-9cce-5911b7bf01a1"),
            "l2b", children = listOf(l2bl3a))

        val root = DecodedStation(
            UUID.fromString("7fdcab1e-c76d-4f0e-3ddf-6917b7bf01a2"),
            "root", children = listOf(l1Node))

        root.setChildren()

        val testString = Fixtures.loadTestFileFromLocation("twoLevelSearchMap.json")
        val searchMap = SearchMap.create(testString)
        val sut = BreadthFirstSearch()
        val result = sut.findNode(nodeToFind, searchMap)
        val resultString = result.toString()
        print(resultString)
        assertNotNull(result)
        assert(nodeToFind.id == result.id
                && nodeToFind.name == result.name)
    }
}