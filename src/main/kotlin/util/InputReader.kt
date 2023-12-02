package util

import java.io.File

class InputReader(
    private val inputFilename: String,
) {

    fun getInputAsString(): String {
        return fromResources().readText()
    }

    fun getInputAsList(): List<String> {
        return fromResources().readLines()
    }

    private fun fromResources(): File {
        return File(javaClass.classLoader.getResource(inputFilename).toURI())
    }
}
