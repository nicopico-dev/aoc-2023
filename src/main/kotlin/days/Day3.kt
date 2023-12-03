package days

class Day3(
    inputFileNameOverride: String? = null,
) : Day(3, inputFileNameOverride) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
    }

    class Schematic(
        private val data: String
    ) {
        val width: Int = data.indexOf("\n")
        val height: Int = data.count { it == '\n' }

        fun getCharacterAt(x: Int, y: Int): Char {
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
}
