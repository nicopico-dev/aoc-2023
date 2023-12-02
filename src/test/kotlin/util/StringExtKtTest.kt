package util

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


class StringExtKtTest {

    @Test
    fun `replaceLast should only replace the last occurence`() {
        val sentence = "Practices news never ensures paragraph mailto pages paragraph, bacterial song josh."

        val actual = sentence.replaceLast("paragraph", "phrase")

        actual shouldBe "Practices news never ensures paragraph mailto pages phrase, bacterial song josh."
    }

    @Test
    fun `replaceLast should not do anything if the word is not found`() {
        val sentence = "Sept lecture hidden equivalent."

        val actual = sentence.replaceLast("foo", "bar")

        actual shouldBe "Sept lecture hidden equivalent."
    }
}
