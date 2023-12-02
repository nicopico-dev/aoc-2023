package days

import io.kotest.matchers.collections.shouldContainOnly
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
        """1two1
            one21
            onetwo1
            one2one
            onetwone
            121
            1twoone
            12one
            """
            .trimIndent()
            .lines()
            .map {
                Day1.extractDigits(it)
            } shouldContainOnly listOf("121")
    }
}
