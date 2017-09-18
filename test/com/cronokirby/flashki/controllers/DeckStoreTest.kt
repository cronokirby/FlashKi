package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Category
import com.cronokirby.flashki.models.Deck
import com.cronokirby.flashki.models.DeckMeta
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class DeckStoreTest {
    @Test
    fun editOut() {
        val store = DeckStore()
        val d = Deck(listOf(), DeckMeta(Category("japanese"), "foo"))
        store.editOut(Deck.empty(), d)
        assertTrue(store.decks.contains(d))
        val d2 = d.copy(listOf(), DeckMeta(Category("japanese"), "new name"))
        store.editOut(d, d2)
        assertTrue(store.decks.contains(d2))
        assertFalse(store.decks.contains(d))
        assertEquals(1, store.decks.size)
    }
}