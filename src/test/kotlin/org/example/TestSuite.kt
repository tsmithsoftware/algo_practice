package org.example

import org.junit.Test

class TestSuite {

    @Test
    fun runTest() {
        val args = arrayOf("hello")
        main(args)
        assert(true)
    }
}
