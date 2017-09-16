package com.cronokirby.flashki.controllers

import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Deck
import tornadofx.Controller


class DeckEditor(val deck: Deck) : Controller() {

    private enum class State {
        FirstCard,
        SomeCard,
        LastCard,
        NewCard
    }

    var currentCard = Card("", "")
        get
        private set
    private val editingState
        get() = when(index) {
            0 -> State.FirstCard
            deck.cardCount - 1 -> State.LastCard
            deck.cardCount -> State.NewCard
            else -> State.SomeCard
        }
    private var index = deck.cardCount

    fun moveLeft() {
       when (editingState) {
           State.FirstCard -> {}
           State.SomeCard, State.LastCard -> {
               index -= 1
               currentCard = deck.cardList[index]
           }
           State.NewCard -> {
               deck.add(currentCard)
               index -= 1
               currentCard = deck.cardList[index]
           }
       }
    }

    fun moveRight() {
        when (editingState) {
            State.FirstCard, State.SomeCard -> {
                index += 1
                currentCard = deck.cardList[index]
            }
            State.LastCard -> {
                index += 1
                currentCard = Card("", "")
            }
            State.NewCard -> {
                index += 1
                deck.add(currentCard)
                currentCard = Card("", "")
            }
        }
    }
}