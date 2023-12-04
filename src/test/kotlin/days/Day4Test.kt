package days

import days.Day4.CardId
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {

    //region Part 1
    @Test
    fun `Card can be parsed`() {
        val card = Day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        assertSoftly(card) {
            id shouldBe CardId(1)
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
    //endregion

    @Test
    fun `A card can win some card copies`() {
        val card1 = Day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        val card2 = Day4.parseCard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")
        val card5 = Day4.parseCard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36")

        assertSoftly {
            card1.computeWonCopies() shouldBe listOf(2, 3, 4, 5).map(::CardId)
            card2.computeWonCopies() shouldBe listOf(3, 4).map(::CardId)
            card5.computeWonCopies() shouldBe emptyList()
        }
    }

    @Test
    fun `Processing cards will collect a bunch of cards`() {
        val day = Day4()

        day.processCards() shouldBe mapOf(
            CardId(1) to 1,
            CardId(2) to 2,
            CardId(3) to 4,
            CardId(4) to 8,
            CardId(5) to 14,
            CardId(6) to 1,
        )
    }

    @Test
    fun partTwo() {
        val day = Day4()

        day.partTwo() shouldBe 30
    }
}
