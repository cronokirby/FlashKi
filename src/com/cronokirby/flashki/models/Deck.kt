package com.cronokirby.flashki.models

import java.time.LocalDate

/**
 * A deck of cards to study
 *
 * Contains the list of cards, as well as metadata surrounding them.
 *
 * @param cards the list of cards this deck will contain
 * @param category the initial set of tags this deck should have
 */
data class Deck(val cards: List<Card>, val metaData: DeckMeta) {
    val cardCount = cards.size
}