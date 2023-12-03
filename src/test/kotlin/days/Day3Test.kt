package days

import io.kotest.assertions.assertSoftly
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
}
