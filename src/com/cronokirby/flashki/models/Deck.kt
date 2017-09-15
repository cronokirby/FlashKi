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
    private val cardMap = cards.associateByTo(hashMapOf(), {it.id}, {it})
    val cardList // we don't want this to be mutable
        get() = cardMap.values.toList()
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
     * Uses the card's id to determine which card to replace.
     * @param card the card to replace
     * @param edited the card with which to replace it
     */
    fun edit(card: Card, edited: Card) {
        // we could just use the id instead of the whole card, but having it like this
        // means that changing the card's id implementation won't break outside code
        cardMap.remove(card.id)
        cardMap.put(edited.id, edited)
    }
}