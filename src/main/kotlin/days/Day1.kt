package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList
            .sumOf { line ->
                val first = line.first(Char::isDigit)
                val last = line.last(Char::isDigit)
                "$first$last".toInt()
            }
    }

    override fun partTwo(): Any {
        return "TODO"
    }
}
