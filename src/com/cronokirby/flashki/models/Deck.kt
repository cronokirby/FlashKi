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
class Deck(cards: Collection<Card>, val category: List<String>, var name: String) {
    val cardMap = cards.associateByTo(hashMapOf(), {it.id}, {it})
    val createdAt = LocalDate.now()
    val cardCount
        get() = cardMap.size

    /**
     * Adds a new card to the deck
     *
     * @param card the card to add to the deck
     * @return null if the card was NOT in the map (which should be the case)
     */
    fun add(card: Card): Card? {
        return cardMap.putIfAbsent(card.id, card)
    }

    /**
     * Replaces a card in the deck with an edited version
     *
     * Uses the card's id to determine which card to replace
     * @param card the card to replaced the old one in the deck
     */
    fun edit(card: Card) {
        cardMap.put(card.id, card)
    }
}