package days

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
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
            Day1.replaceSpelledDigits("eightwothree") shouldBe "8wo3"
            Day1.replaceSpelledDigits("zoneight234") shouldBe "z1ight234"
        }
    }
}
