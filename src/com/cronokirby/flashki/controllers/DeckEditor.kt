package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Deck
import com.cronokirby.flashki.models.DeckMeta
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*


class DeckEditor(val deck: Deck = Deck(listOf(), DeckMeta("", ""))) : Controller() {

    private enum class State {
        FirstCard,
        SomeCard,
        LastCard,
        NewCard
    }

    private val cards = deck.cards.toMutableList()
    private var index = cards.size
    val cardProp = SimpleObjectProperty<Card>(Card("", ""))
    var currentCard by cardProp

    private val editingState
        get() = when(index) {
            cards.size - 1 -> State.LastCard
            cards.size -> State.NewCard
            0 -> State.FirstCard
            else -> State.SomeCard
        }

    fun moveLeft() {
       when (editingState) {
           State.FirstCard -> {}
           State.SomeCard, State.LastCard -> {
               cards[index] = currentCard
               index -= 1
               currentCard = cards[index]
           }
           State.NewCard -> {
               cards.add(currentCard)
               index -= 1
               currentCard = cards[index]
           }
       }
    }

    fun moveRight() {
        when (editingState) {
            State.FirstCard, State.SomeCard -> {
                cards[index] = currentCard
                index += 1
                currentCard = cards[index]
            }
            State.LastCard -> {
                cards[index] = currentCard
                index += 1
                currentCard = Card("", "")
            }
            State.NewCard -> {
                index += 1
                cards.add(currentCard)
                currentCard = Card("", "")
            }
        }
    }
}