package days

import days.Day2.Color.Blue
import days.Day2.Color.Green
import days.Day2.Color.Red
import days.Day2.Game
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

    private val Int.red get() = Red to this
    private val Int.green get() = Green to this
    private val Int.blue get() = Blue to this

    @Test
    fun `parseInput should extract the correct information from input`() {
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
}
