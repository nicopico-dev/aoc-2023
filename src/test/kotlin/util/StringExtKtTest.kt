package util

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


class StringExtKtTest {

    @Test
    fun `replaceLast should only replace the last occurrence`() {
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

    @Test
    fun `allIndexesOf should return a list of all indexes of a word`() {
        val sentence = "Device minolta walks creativity addition least walks compromise, adsl shower walks described russian."

        val actual = sentence.allIndexesOf("walks")

        actual shouldBe listOf(15, 47, 77)
    }

    @Test
    fun `allIndexesOf should return an empty list if the word is not found`() {
        val sentence = "Catalogs gnome lightweight oils purse evans must, arrest structures indeed pit greene academics economic."

        val actual = sentence.allIndexesOf("walks")

        actual shouldBe listOf()
    }
}
