package days

class Day4 : Day(4) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
    }

    data class Card(
        val id: Int,
        val winningNumbers: Set<Int>,
        val numbersYouHave: Set<Int>,
    )

    companion object {
        private val cardRegex = Regex("Card (\\d+): ([\\d\\s]+)\\|([\\d\\s]+)")
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
    }
}
