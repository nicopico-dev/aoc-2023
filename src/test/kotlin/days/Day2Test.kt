package days

import days.Day2.Color.Blue
import days.Day2.Color.Green
import days.Day2.Color.Red
import days.Day2.Game
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `parseInput should extract the correct information from input`() {
        val actual = Day2.parseInput("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        actual shouldBe Game(
            id = 1,
            takes = listOf(
                mapOf(
                    Blue to 3,
                    Red to 4,
                ),
                mapOf(
                    Red to 1,
                    Green to 2,
                    Blue to 6,
                ),
                mapOf(
                    Green to 2,
                )
            )
        )
    }
}
