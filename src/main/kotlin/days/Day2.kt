package days

import kotlin.math.max

class Day2(
    inputFileNameOverride: String? = null,
) : Day(2, inputFileNameOverride) {

    override fun partOne(): Any {
        val hypothesis = Hypothesis(
            red = 12,
            green = 13,
            blue = 14,
        )
        return inputList
            .map { parseInput(it) }
            .filter { hypothesis isPossibleWith it }
            .sumOf { it.id }
    }

    override fun partTwo(): Any {
        return inputList
            .map { parseInput(it) }
            .map { it.getMinimumHypothesis() }
            .sumOf { it.power }
    }

    companion object {
        private val gameRegex = Regex("Game (\\d+):")
        private val cubeRegex = Regex("(\\d+) (red|green|blue)")

        fun parseInput(input: String): Game {
            val gameId = gameRegex.find(input)!!.groupValues[1]
            val takes = input.substringAfter(":")
                .split(";")
                .map { take ->
                    cubeRegex
                        .findAll(take)
                        .associate {
                            val color = Color.parse((it.groupValues[2]))
                            val count = (it.groupValues[1]).toInt()
                            color to count
                        }
                }

            return Game(
                id = gameId.toInt(),
                takes = takes
            )
        }
    }

    data class Game(
        val id: Int,
        val takes: List<Map<Color, Int>>
    ) {
        fun getMinimumHypothesis(): Hypothesis {
            return takes.fold(Hypothesis()) { acc, take ->
                Hypothesis(
                    red = max(acc.red, take.getOrDefault(Color.Red, 0)),
                    green = max(acc.green, take.getOrDefault(Color.Green, 0)),
                    blue = max(acc.blue, take.getOrDefault(Color.Blue, 0)),
                )
            }
        }
    }

    data class Hypothesis(
        val red: Int = 0,
        val green: Int = 0,
        val blue: Int = 0,
    ) {
        val power = red * green * blue

        infix fun isPossibleWith(game: Game): Boolean {
            return game.takes.all { take ->
                (take[Color.Red] ?: 0) <= red
                        && (take[Color.Green] ?: 0) <= green
                        && (take[Color.Blue] ?: 0) <= blue
            }
        }
    }

    enum class Color {
        Red,
        Green,
        Blue;

        companion object {
            fun parse(value: String): Color {
                return when (value) {
                    "red" -> Red
                    "green" -> Green
                    "blue" -> Blue
                    else -> throw IllegalArgumentException("Unknown color $value")
                }
            }
        }
    }
}
