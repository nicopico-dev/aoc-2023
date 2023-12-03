package days

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
            getCharacterAt(x = 0, y = 0) shouldBe '4'
            getCharacterAt(x = 0, y = 9) shouldBe '.'
            getCharacterAt(x = 3, y = 1) shouldBe '*'
            getCharacterAt(x = 0, y = 4) shouldBe '6'
        }
    }

    @Test
    fun `Trying to get character from an invalid point coordinate will throw an exception`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Schematic(data)

        assertSoftly(schematic) {
            withClue("Negative X") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(x = -1, y = 0)
                }
            }
            withClue("Negative Y") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(x = 0, y = -1)
                }
            }
            withClue("Too big X") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(x = 0, y = 10)
                }
            }
            withClue("Too big Y") {
                shouldThrow<IllegalArgumentException> {
                    getCharacterAt(x = 10, y = 0)
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
}
