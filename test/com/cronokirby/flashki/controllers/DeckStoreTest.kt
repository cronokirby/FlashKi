package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Category
import com.cronokirby.flashki.models.Deck
import com.cronokirby.flashki.models.DeckMeta
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class DeckStoreTest {
    @Test
    fun addDeck() {
        val store = DeckStore()
        val d = Deck(listOf(), DeckMeta(Category("japanese"), "foo"))
        store.addDeck(d)
        store.addDeck(d)
        assertTrue(store.decks.contains(d))
    }

    @Test
    fun addRaw() {
        val store = DeckStore()
        val d = Deck(listOf(), DeckMeta(Category("japanese"), "foo"))
        store.addRaw(listOf(), Category("japanese"), "foo")
        assertTrue(store.decks.contains(d))
    }
}