package com.cronokirby.flashki.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DeckTest {
    @Test
    fun add() {
        val deck = Deck(listOf(), listOf("vocab", "japanese"), "deck1")
        assertEquals(deck.add(Card("hello", "bonjour")), null)
        // shouldn't be null since the card is already in the deck
        assertNotEquals(deck.add(Card("hello", "bonjour")), null)
        // the card list should only have this card
        assertEquals(deck.cardCount, 1)
    }

    @Test
    fun edit() {
        val oldCard = Card("hello", "bojour")
        val l1 = listOf(oldCard, Card("goodbye", "aurev"))
        val deck = Deck(l1, listOf(), "deck1")
        assertTrue(deck.cardList.containsAll(l1))
        val newCard = Card("hello", "bonjour")
        deck.edit(oldCard, newCard)
        assertTrue(deck.cardList.contains(newCard))
        assertFalse(deck.cardList.contains(Card("hello", "bojour")))
        assertEquals(2, deck.cardCount)
    }

}