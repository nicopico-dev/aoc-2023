package days

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun partOne() {
        val dayOne = Day1("input_day_1A.txt")
        dayOne.partOne() shouldBe 142
    }

    @Test
    fun partTwo() {
        val dayOne = Day1("input_day_1B.txt")
        dayOne.partTwo() shouldBe 281
    }

    @Test
    fun `replaceSpelledDigits should handle overlapping digit names`() {
        assertSoftly {
            Day1.replaceSpelledDigits("eightwothree") should {
                it shouldStartWith "8"
                it shouldEndWith "3"
            }
            Day1.replaceSpelledDigits("zoneight234") should {
                it shouldStartWith "z1"
            }
            Day1.replaceSpelledDigits("8five864sixfive") should {
                it shouldStartWith "85"
                it shouldEndWith "5"
            }
            Day1.replaceSpelledDigits("65five") should {
                it shouldBe "655"
            }
        }
    }
}
