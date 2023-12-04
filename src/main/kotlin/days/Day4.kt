package days

import kotlin.math.pow

class Day4 : Day(4) {

    private val cards: List<Card> by lazy {
        inputList.map { parseCard(it) }
    }

    override fun partOne(): Any {
        return cards
            .map { it.winningNumbersYouHave.size }
            .sumOf { computePoints(winCount = it) }
    }

    override fun partTwo(): Any {
        return processCards().values.sum()
    }

    /**
     * Compute the number of each card the user as won as the end
     */
    fun processCards(): Map<CardId, Int> {
        val allCards: List<Card> = cards +
                cards.flatMap { processCard(it) }
        return allCards
            .groupBy { it.id }
            .mapValues { it.value.size }
    }

    /**
     * Return *all* cards won by [card]
     */
    private fun processCard(card: Card): List<Card> {
        val wonCards = getWonCards(card)
        return if (wonCards.isEmpty()) emptyList()
        // Each won card must be processed as well
        else wonCards + wonCards.flatMap { processCard(it) }
    }

    /**
     * Return the first round of cards won by [card]
     */
    private fun getWonCards(card: Card): List<Card> {
        return card.computeWonCopies()
            .map { cardId ->
                cards.first { it.id == cardId }
            }
    }

    data class Card(
        val id: CardId,
        val winningNumbers: Set<Int>,
        val numbersYouHave: Set<Int>,
    ) {
        val winningNumbersYouHave: Set<Int> = winningNumbers intersect numbersYouHave

        fun computeWonCopies(): List<CardId> {
            return if (winningNumbersYouHave.isEmpty()) {
                emptyList()
            } else {
                val winCount = winningNumbersYouHave.size
                var cardId = id
                buildList {
                    repeat(winCount) {
                        cardId = cardId.next()
                        add(cardId)
                    }
                }
            }
        }
    }

    @JvmInline
    value class CardId(val id: Int) {
        fun next() = CardId(id + 1)
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
                id = CardId(id.toInt()),
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
