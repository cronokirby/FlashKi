package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Category
import com.cronokirby.flashki.models.Deck
import com.cronokirby.flashki.models.DeckMeta
import javafx.collections.FXCollections
import tornadofx.Controller

class DeckStore : Controller() {
    val decks = FXCollections.observableArrayList<Deck>()

    fun addDeck(deck: Deck) {
        decks.add(deck)
    }

    fun addRaw(cards: Collection<Card>, category: Category, name: String) {
        val deck = Deck(cards.toList(), DeckMeta(category, name))
        addDeck(deck)
    }
}