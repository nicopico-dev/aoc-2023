package util

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InputReaderTest {

    private lateinit var inputReader: InputReader

    @BeforeEach
    fun setUp() {
        inputReader = InputReader("input_day_0.txt")
    }

    @Test
    fun testReadInputAsString() {
        val testInputAsString = inputReader.getInputAsString()
        testInputAsString shouldBe "this\nis\na\ntest input\nfile\n"
    }

    @Test
    fun testReadInputAsList() {
        val testInputAsList = inputReader.getInputAsList()
        testInputAsList shouldContainExactly listOf("this", "is", "a", "test input", "file")
    }
}
