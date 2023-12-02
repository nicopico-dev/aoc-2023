package days

import days.Day2.Color.Blue
import days.Day2.Color.Green
import days.Day2.Color.Red
import days.Day2.Game
import days.Day2.Hypothesis
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

    private val Int.red get() = Red to this
    private val Int.green get() = Green to this
    private val Int.blue get() = Blue to this

    @Test
    fun `parse input to extract a game`() {
        val actual = Day2.parseInput("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        actual shouldBe Game(
            id = 1,
            takes = listOf(
                mapOf(
                    3.blue,
                    4.red,
                ),
                mapOf(
                    1.red,
                    2.green,
                    6.blue,
                ),
                mapOf(
                    2.green,
                )
            )
        )
    }

    @Test
    fun `check hypothesis to tell if a game is possible`() {
        val hypothesis = Hypothesis(
            red = 12,
            green = 13,
            blue = 14,
        )

        val game1 = Day2.parseInput("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        val game3 = Day2.parseInput("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")

        assertSoftly {
            (hypothesis isPossibleWith game1) shouldBe true
            (hypothesis isPossibleWith game3) shouldBe false
        }
    }
}
