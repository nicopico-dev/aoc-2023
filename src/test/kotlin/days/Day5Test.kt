package days

import days.Day5.Almanac
import days.Day5.IdMap
import days.Day5.IdMapSection
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `Almanach can be loaded from input`() {
        val day = Day5()

        val almanac: Almanac = day.loadAlmanac()

        assertSoftly(almanac) {
            seeds shouldBe listOf(79, 14, 55, 13)
            seedToSoil shouldBe IdMapSection(
                    listOf(
                            IdMap(50, 98, 2),
                            IdMap(52, 50, 48),
                    ),
            )
            lightToTemperature shouldBe IdMapSection(
                    listOf(
                            IdMap(45, 77, 23),
                            IdMap(81, 45, 19),
                            IdMap(68, 64, 13),
                    ),
            )
        }
    }
}