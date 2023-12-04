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

    @Test
    fun `Card can compute the number of winning number you have`() {
        val card = Day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        card.winningNumbersYouHave shouldBe setOf(48, 83, 17, 86)
    }

    @Test
    fun `Card points should depend on how many winning number you have`() {
        assertSoftly {
            Day4.computePoints(winCount = 4) shouldBe 8
            Day4.computePoints(winCount = 2) shouldBe 2
            Day4.computePoints(winCount = 1) shouldBe 1
            Day4.computePoints(winCount = 0) shouldBe 0
        }
    }

    @Test
    fun partOne() {
        val day = Day4()
        day.partOne() shouldBe 13
    }
}
