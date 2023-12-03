package days

class Day3(
    inputFileNameOverride: String? = null,
) : Day(3, inputFileNameOverride) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
    }

    class Schematic(
        description: String
    ) {
        val width: Int = description.indexOf("\n")
        val height: Int = description.count { it == '\n' }
    }
}
