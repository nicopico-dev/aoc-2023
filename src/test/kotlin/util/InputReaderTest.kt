package util

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InputReaderTest {

    @Test
    fun testReadInputAsString() {
        val testInputAsString = InputReader.getInputAsString(0)
        testInputAsString shouldBe "this\nis\na\ntest input\nfile\n"
    }

    @Test
    fun testReadInputAsList() {
        val testInputAsList = InputReader.getInputAsList(0)
        testInputAsList shouldContainExactly listOf("this", "is", "a", "test input", "file")
    }
}
