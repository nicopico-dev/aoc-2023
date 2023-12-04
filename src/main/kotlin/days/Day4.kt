package days

import kotlin.math.pow

class Day4 : Day(4) {
    override fun partOne(): Any {
        return inputList
            .map { parseCard(it) }
            .map { it.winningNumbersYouHave.size }
            .sumOf { computePoints(winCount = it) }
    }

    data class Card(
        val id: Int,
        val winningNumbers: Set<Int>,
        val numbersYouHave: Set<Int>,
    ) {
        val winningNumbersYouHave: Set<Int> = winningNumbers intersect numbersYouHave
    }

    companion object {
        private val cardRegex = Regex("Card\\s+(\\d+): ([\\d\\s]+)\\|([\\d\\s]+)")
        private val spaceRegex = Regex("\\s+")

        fun parseCard(data: String): Card {
            val match = cardRegex.matchEntire(data)
            require(match != null) {
                "Invalid format for Card ($data)"
            }

            val (id, winningNumbers, numbersYouHave) = match.destructured
            return Card(
                id = id.toInt(),
                winningNumbers = winningNumbers.extractNumbers(),
                numbersYouHave = numbersYouHave.extractNumbers(),
            )
        }

        private fun String.extractNumbers() = this
            .trim()
            .split(spaceRegex)
            .map(String::toInt)
            .toSet()

        fun computePoints(winCount: Int): Int {
            return if (winCount > 0) {
                2.0.pow(winCount - 1.0).toInt()
            } else {
                0
            }
        }
    }
}
