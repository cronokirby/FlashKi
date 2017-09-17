package com.cronokirby.flashki.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CardTest {

    @Test
    fun isThat() {
        val card = Card("hello", "bonjour")
        // It should not be equal to cards with different fields
        assertFalse(card == Card("hello", "hallo"))
        assertFalse(card == Card("bonjour", "hello"))
        // but it should be equal to cards with the same fields
        assertTrue(card == card)
        assertTrue(card == Card("hello", "bonjour"))
    }
}
