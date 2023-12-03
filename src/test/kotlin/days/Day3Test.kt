package days

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `Schematic can be loaded from text`() {
        val schematic = Day3.Schematic(
            """467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...\$.*....
            .664.598..""".trimIndent()
        )

        assertSoftly(schematic) {
            withClue("width") { it.width shouldBe 10 }
            withClue("height") { it.height shouldBe 10 }
        }
    }
}
