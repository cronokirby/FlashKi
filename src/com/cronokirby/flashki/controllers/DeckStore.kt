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
        val i = decks.indexOfFirst { it.metaData == oldDeck.metaData }
        if (i >= 0) {
            decks.removeAt(i)
        }
        decks.add(newDeck)
    }
}