package com.cronokirby.flashki.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CardTest {

    @Test
    fun isThat() {
        val card = Card("hello", "bonjour")
        // It should not be equal to cards with different fields
        assertFalse(card.isThat(Card("hello", "hallo")))
        assertFalse(card.isThat(Card("bonjour", "hello")))
        // but it should be equal to cards with the same fields
        assertTrue(card.isThat(card))
        assertTrue(card.isThat(Card("hello", "bonjour")))
    }
}
