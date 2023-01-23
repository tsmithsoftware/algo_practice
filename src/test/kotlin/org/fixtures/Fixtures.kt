package org.fixtures

import java.io.File
import java.net.URL


class Fixtures {
    companion object {
        fun loadTestFileFromLocation(location: String): String {
            val classLoader: ClassLoader = this.javaClass.classLoader
            val resource: URL? = classLoader.getResource(location)
            resource?.let {
                return File(it.path).readText()
            }
            return ""
        }
    }
}