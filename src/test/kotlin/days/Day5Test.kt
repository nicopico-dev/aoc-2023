package days

import days.Day5.Almanac
import days.Day5.Mapping
import days.Day5.AlmanacSection
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
            seedToSoil shouldBe AlmanacSection(
                    listOf(
                            Mapping(50, 98, 2),
                            Mapping(52, 50, 48),
                    ),
            )
            lightToTemperature shouldBe AlmanacSection(
                    listOf(
                            Mapping(45, 77, 23),
                            Mapping(81, 45, 19),
                            Mapping(68, 64, 13),
                    ),
            )
        }
    }

    @Test
    fun `IdMapSection can give the correspondance between ids`() {
        val section = AlmanacSection(
            listOf(
                Mapping(50, 98, 2),
                Mapping(52, 50, 48),
                ),
            )

        assertSoftly(section) {
            // No mapping -> same number
            getMatchingIdFor(0) shouldBe 0
            getMatchingIdFor(1) shouldBe 1

            getMatchingIdFor(48) shouldBe 48
            getMatchingIdFor(49) shouldBe 49
            getMatchingIdFor(50) shouldBe 52
            getMatchingIdFor(51) shouldBe 53

            getMatchingIdFor(96) shouldBe 98
            getMatchingIdFor(97) shouldBe 99
            getMatchingIdFor(98) shouldBe 50
            getMatchingIdFor(99) shouldBe 51
        }
    }

    @Test
    fun `Almanac can give the location corresponding to a seed`() {
        val day = Day5()
        val almanac: Almanac = day.loadAlmanac()

        assertSoftly {
            almanac.getSeedLocation(79) shouldBe 82
            almanac.getSeedLocation(14) shouldBe 43
            almanac.getSeedLocation(55) shouldBe 86
            almanac.getSeedLocation(13) shouldBe 35
        }
    }

    @Test
    fun partOne() {
        val day = Day5()
        day.partOne() shouldBe 35
    }

    @Test
    fun `list can be build from a start and a length`() {
        Day5.buildSequence(79, 14).toList() shouldBe listOf(
            79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92
        )
    }

    @Test
    fun partTwo() {
        val day = Day5()
        day.partTwo() shouldBe 46
    }
}