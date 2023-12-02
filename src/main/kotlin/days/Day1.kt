package days

import util.replaceLast

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
            .map { replaceSpelledDigits(it) }
            .sumOf { line ->
                val first = line.first(Char::isDigit)
                val last = line.last(Char::isDigit)
                "$first$last".toInt()
            }
    }

    companion object {
        private val spelledDigits = mapOf(
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

        fun replaceSpelledDigits(line: String): String {
            var firstSpelled: Spelled? = null
            var lastSpelled: Spelled? = null
            spelledDigits.keys.forEach { word ->
                line.indexOf(word).let { index ->
                    if (index >= 0 && index < (firstSpelled?.startIndex ?: Int.MAX_VALUE)) {
                        firstSpelled = Spelled(word, index)
                    }
                }
                line.lastIndexOf(word).let { index ->
                    if (index >= 0 && (index + word.length) > (lastSpelled?.endIndex ?: Int.MIN_VALUE)) {
                        lastSpelled = Spelled(word, index)
                    }
                }
            }

            return line
                .let {
                    firstSpelled?.let { spelled ->
                        // Only replace the first occurrence
                        it.replaceFirst(spelled.word, spelledDigits[spelled.word]!!)
                    } ?: it
                }
                .let {
                    lastSpelled?.let { spelled ->
                        it.replaceLast(spelled.word, spelledDigits[spelled.word]!!)
                    } ?: it
                }
        }
    }

    private data class Spelled(
        val word: String,
        val startIndex: Int,
    ) {
        val endIndex = startIndex + word.length
    }
}
