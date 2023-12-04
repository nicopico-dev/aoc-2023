package days

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `Card can be parsed`() {
        val card = Day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        assertSoftly(card) {
            id shouldBe 1
            winningNumbers shouldBe setOf(41, 48, 83, 86, 17)
            numbersYouHave shouldBe setOf(83, 86, 6, 31, 17, 9, 48, 53)
        }
    }
}
