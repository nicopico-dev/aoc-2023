package days

class Day3(
    inputFileNameOverride: String? = null,
) : Day(3, inputFileNameOverride) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
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
