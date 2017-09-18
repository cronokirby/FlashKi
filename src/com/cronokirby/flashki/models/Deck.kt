package com.cronokirby.flashki.models

/**
 * A deck of cards to study
 *
 * Contains the list of cards, as well as metadata surrounding them.
 *
 * @param cards the list of cards this deck will contain
 * @param metaData the metadata associated with this deck
 */
class Deck(val cards: List<Card>, val metaData: DeckMeta) {
    val cardCount = cards.size

    companion object {
        fun empty(): Deck {
            return Deck(listOf(), DeckMeta(Category(""), ""))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Deck

        if (metaData != other.metaData) return false
        if (cardCount != other.cardCount) return false

        return true
    }

    override fun hashCode(): Int {
        return metaData.hashCode()
    }
}