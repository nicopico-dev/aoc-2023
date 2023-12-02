package days

import util.allIndexesOf

class Day1(
    inputFileNameOverride: String? = null,
) : Day(1, inputFileNameOverride) {

    override fun partOne(): Any {
        return inputList
            .sumOf { line ->
                val first = line.first(Char::isDigit)
                val last = line.last(Char::isDigit)
                "$first$last".toInt()
            }
    }

    override fun partTwo(): Any {
        return inputList
            .map { extractDigits(it) }
            .sumOf { line ->
                val first = line.first()
                val last = line.last()
                "$first$last".toInt()
            }
    }

    companion object {
        private val spelledDigits = mapOf(
            "1" to "1",
            "2" to "2",
            "3" to "3",
            "4" to "4",
            "5" to "5",
            "6" to "6",
            "7" to "7",
            "8" to "8",
            "9" to "9",

            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )

        fun extractDigits(line: String): String {
            return spelledDigits.keys
                .flatMap { word ->
                    line.allIndexesOf(word)
                        .map { index ->
                            Digit(word, index)
                        }
                }
                .sortedBy { it.startIndex }
                .joinToString(separator = "") {
                    spelledDigits[it.word]!!
                }
        }
    }

    private data class Digit(
        val word: String,
        val startIndex: Int,
    )
}
