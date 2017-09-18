package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Category
import com.cronokirby.flashki.models.Deck
import com.cronokirby.flashki.models.DeckMeta
import javafx.collections.FXCollections
import tornadofx.Controller

class DeckStore : Controller() {
    val decks = FXCollections.observableArrayList<Deck>()

    fun editOut(oldDeck: Deck, newDeck: Deck) {
        decks.remove(oldDeck)
        decks.add(newDeck)
    }
}