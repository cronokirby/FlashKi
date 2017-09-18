package com.cronokirby.flashki.models

import java.time.LocalDate

/**
 * A deck of cards to study
 *
 * Contains the list of cards, as well as metadata surrounding them.
 *
 * @param cards the list of cards this deck will contain
 * @param metaData the metadata associated with this deck
 */
data class Deck(val cards: List<Card>, val metaData: DeckMeta) {
    val cardCount = cards.size

    companion object {
        fun empty(): Deck {
            return Deck(listOf(), DeckMeta(Category(""), ""))
        }
    }
}