package days

class Day3(
    inputFileNameOverride: String? = null,
) : Day(3, inputFileNameOverride) {
    override fun partOne(): Any {
        return getPartNumbers()
            .sum()
    }

    internal fun getPartNumbers(): List<Int> {
        val schematic = Schematic(inputString)
        return schematic
            .getSymbolPoints()
            .flatMap {
                it.computeAdjacentPoints(
                    width = schematic.width,
                    height = schematic.height,
                )
            }
            .filter { point ->
                schematic.getCharacterAt(point).isDigit()
            }
            .map { point ->
                schematic.getNumberStartingPoint(point)
            }
            .distinct()
            .map { point ->
                schematic.getWholeNumberStartingAt(point)
            }
    }

    class Schematic(private val data: String) {
        val width: Int = data.indexOf("\n")
        val height: Int = data.count { it == '\n' }

        @Deprecated(
            "User getCharacterAt(point)",
            replaceWith = ReplaceWith("this.getCharacterAt(Point(x, y))")
        )
        fun getCharacterAt(x: Int, y: Int): Char {
            return getCharacterAt(Point(x = x, y = y))
        }

        fun getCharacterAt(point: Point): Char {
            val (x, y) = point
            require(x in 0..<width) {
                "x should be in range [0, $width[ ($x)"
            }
            require(y in 0..<height) {
                "y should be in range [0, $height[ ($y)"
            }

            // Add 1 to width to count the `\n` character
            val index = (y * (width + 1)) + x

            return data[index]
        }

        fun getSymbolPoints(): List<Point> {
            val symbolRegex = Regex("[^.0-9\\s]")
            return symbolRegex.findAll(data.replace("\n", ""))
                .map { match ->
                    val index = match.range.first
                    Point(
                        x = index % width,
                        y = index / width,
                    )
                }.toList()
        }

        fun getNumberStartingPoint(point: Point): Point {
            require(getCharacterAt(point).isDigit()) {
                "Character at $point is not a digit ${getCharacterAt(point)}"
            }
            var x = point.x
            while (x > 0 && getCharacterAt(Point(x - 1, point.y)).isDigit()) {
                x -= 1
            }
            return Point(x, point.y)
        }

        fun getWholeNumberStartingAt(point: Point): Int {
            require(getCharacterAt(point).isDigit()) {
                "Character at $point is not a digit ${getCharacterAt(point)}"
            }
            val buffer = StringBuilder()

            var char: Char = getCharacterAt(point)
            var x = point.x
            do {
                buffer.append(char)
                x += 1
                char = getCharacterAt(Point(x, point.y))
            } while (char.isDigit())

            return buffer.toString().toInt()
        }
    }

    data class Point(val x: Int, val y: Int) {
        fun computeAdjacentPoints(width: Int, height: Int): List<Point> {
            return listOf(
                // Row above
                copy(x = x - 1, y = y - 1),
                copy(y = y - 1),
                copy(x = x + 1, y = y - 1),
                // Same row
                copy(x = x - 1),
                copy(x = x + 1),
                // Row below
                copy(x = x - 1, y = y + 1),
                copy(y = y + 1),
                copy(x = x + 1, y = y + 1),
            ).filter {
                // Remove all out-of-bounds points
                it.x in 0..<width && it.y in 0..<height
            }
        }
    }
}
