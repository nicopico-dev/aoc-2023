package days

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
        val spelledDigits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )

        fun replaceSpelledDigits(line: String): String {
            var tmp = line
            spelledDigits.forEach { (spelled, value) ->
                tmp = tmp.replace(Regex(spelled), value.toString())
            }
            return tmp
        }
    }
}
