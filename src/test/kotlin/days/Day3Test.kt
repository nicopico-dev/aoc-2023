package days

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import util.InputReader

class Day3Test {

    @Test
    fun `Schematic can be loaded from text`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Day3.Schematic(data)

        assertSoftly(schematic) {
            withClue("width") { it.width shouldBe 10 }
            withClue("height") { it.height shouldBe 10 }
        }
    }

    @Test
    fun `Schematic can load a character from a point coordinates`() {
        val data = InputReader("input_day_3.txt").getInputAsString()
        val schematic = Day3.Schematic(data)

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
        val schematic = Day3.Schematic(data)

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
}
