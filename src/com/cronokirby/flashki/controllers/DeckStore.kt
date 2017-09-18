package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Deck
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import tornadofx.Controller

class DeckStore : Controller() {
    val decks = FXCollections.observableArrayList<Deck>()

    fun editOut(oldDeck: Deck, newDeck: Deck) {
        decks.remove(oldDeck)
        decks.add(newDeck)
    }
}