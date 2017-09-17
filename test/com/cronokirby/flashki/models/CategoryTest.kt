package com.cronokirby.flashki.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CategoryTest {

    @Test
    fun nextCategory() {
        val cat = Category("decks", "japanese")
        assertEquals(cat.nextCategory(), Category("japanese"))
        assertEquals(cat.nextCategory()?.nextCategory(), null)
    }

    @Test
    fun asString() {
        val cat = Category("decks", "japanese")
        assertEquals(cat.toString(), "decks/japanese")
    }
}