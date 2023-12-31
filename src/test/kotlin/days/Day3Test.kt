package days

import days.Day3.GearRatio
import days.Day3.Point
import days.Day3.Schematic
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import util.InputReader

class Day3Test {

    //region Schematic
    @Test
    fun `Schematic can be loaded from text`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            withClue("width") { it.width shouldBe 10 }
            withClue("height") { it.height shouldBe 10 }
        }
    }

    @Test
    fun `Schematic can load a character from a point coordinates`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            getCharacterAt(Point(x = 0, y = 0)) shouldBe '4'
            getCharacterAt(Point(x = 0, y = 9)) shouldBe '.'
            getCharacterAt(Point(x = 3, y = 1)) shouldBe '*'
            getCharacterAt(Point(x = 0, y = 4)) shouldBe '6'
        }
    }

    @Test
    fun `Schematic can retrieve all symbols points`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        schematic.getSymbolPoints() shouldContainExactlyInAnyOrder listOf(
            Point(x = 3, y = 1),    // *
            Point(x = 6, y = 3),    // #
            Point(x = 3, y = 4),    // *
            Point(x = 5, y = 5),    // +
            Point(x = 3, y = 8),    // $
            Point(x = 5, y = 8),    // *
        )
    }

    @Test
    fun `Schematic can retrieve the starting point of a number`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            getNumberStartingPoint(Point(x = 1, y = 0)) shouldBe Point(x = 0, y = 0)
            getNumberStartingPoint(Point(x = 3, y = 9)) shouldBe Point(x = 1, y = 9)
        }
    }

    @Test
    fun `An exception should be thrown when retrieving the starting point of a non-existant number`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        shouldThrow<IllegalArgumentException> {
            schematic.getNumberStartingPoint(Point(0, 1))
        }
    }

    @Test
    fun `Schematic can retrieve a whole number from its starting point`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            getWholeNumberStartingAt(Point(x = 0, y = 0)) shouldBe 467
            getWholeNumberStartingAt(Point(x = 1, y = 9)) shouldBe 664
        }
    }

    @Test
    fun `An exception should be thrown when retrieving the number from an invalid starting point`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        shouldThrow<IllegalArgumentException> {
            schematic.getWholeNumberStartingAt(Point(0, 1))
        }
    }
    //endregion

    //region Point
    @Test
    fun `Trying to get character from an invalid point coordinate will throw an exception`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            withClue("Negative X") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(Point(x = -1, y = 0))
                }
            }
            withClue("Negative Y") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(Point(x = 0, y = -1))
                }
            }
            withClue("Too big X") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(Point(x = 0, y = 10))
                }
            }
            withClue("Too big Y") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(Point(x = 10, y = 0))
                }
            }
        }
    }

    @Test
    fun `Point can give a list of all adjacent points`() {
        val pointA = Point(x = 0, y = 0)
        val pointB = Point(x = 3, y = 1)
        val pointC = Point(x = 8, y = 9)

        assertSoftly {
            pointA.computeAdjacentPoints(width = 10, height = 10) shouldContainExactlyInAnyOrder listOf(
                Point(x = 1, y = 0),
                Point(x = 0, y = 1), Point(x = 1, y = 1),
            )

            pointB.computeAdjacentPoints(width = 10, height = 10) shouldContainExactlyInAnyOrder listOf(
                Point(x = 2, y = 0), Point(x = 3, y = 0), Point(x = 4, y = 0),
                Point(x = 2, y = 1), Point(x = 4, y = 1),
                Point(x = 2, y = 2), Point(x = 3, y = 2), Point(x = 4, y = 2),
            )

            pointC.computeAdjacentPoints(width = 10, height = 10) shouldContainExactlyInAnyOrder listOf(
                Point(x = 7, y = 8), Point(x = 8, y = 8), Point(x = 9, y = 8),
                Point(x = 7, y = 9), Point(x = 9, y = 9),
            )
        }
    }
    //endregion

    //region Part 1
    @Test
    fun partNumbers() {
        val day = Day3()
        day.getPartNumbers() shouldContainExactlyInAnyOrder
                listOf(467, 35, 633, 617, 592, 755, 664, 598)
    }

    @Test
    fun partOne() {
        val day = Day3()
        day.partOne() shouldBe 4361
    }
    //endregion

    @Test
    fun getGearRatios() {
        val day = Day3()

        day.getGearRatios() shouldContainExactlyInAnyOrder
                listOf(GearRatio(467, 35), GearRatio(755, 598))
    }

    @Test
    fun `Gear ratio should have the right value`() {
        assertSoftly {
            GearRatio(467, 35).value shouldBe 16345
            GearRatio(755, 598).value shouldBe 451490
        }
    }

    @Test
    fun partTwo() {
        val day = Day3()
        day.partTwo() shouldBe 467835
    }
}
