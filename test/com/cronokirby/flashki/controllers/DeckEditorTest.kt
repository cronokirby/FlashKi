package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Deck
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class DeckEditorTest {

   private fun newEditor(): DeckEditor {
       val cards = listOf(Card("hello", "bonjour"), Card("bye", "tchuss"))
       val deck = Deck(cards, listOf(), "deck1")
       return DeckEditor(deck)
   }

    @Test
    fun moving() {
        val editor = newEditor()
        editor.moveLeft()
        assertTrue(editor.currentCard.isThat(Card("bye", "tchuss")))
        editor.moveLeft()
        assertTrue(editor.currentCard.isThat(Card("hello", "bonjour")))
        // moving left when at the end should stay at the end
        editor.moveLeft()
        assertTrue(editor.currentCard.isThat(Card("hello", "bonjour")))
        // we should get back to the new card
        editor.moveRight()
        editor.moveRight()
        assertTrue(editor.currentCard.isThat(Card("", "")))
        // moving further beyond should still give us a new card
        editor.moveRight()
        assertTrue(editor.currentCard.isThat(Card("", "")))
    }

}