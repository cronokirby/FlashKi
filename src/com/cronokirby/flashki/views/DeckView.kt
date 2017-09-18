package com.cronokirby.flashki.views

import com.cronokirby.flashki.controllers.DeckStore
import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import com.cronokirby.flashki.models.Deck
import com.github.thomasnield.rxkotlinfx.changes
import com.github.thomasnield.rxkotlinfx.onChangedObservable
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ListView
import tornadofx.*

class DeckView : View() {
    private val store: DeckStore by inject()

    var deckList: ListView<Deck> by singleAssign()

    init {
        store.decks.changes().subscribe { deckList.refresh() }
    }

    override val root = vbox {
        button("Create a deck") {
            action {
                fire(ChangeViewEvent(ViewPages.NewEditing(Deck.empty())))
            }
        }
        deckList = listview(store.decks) {
           cellFormat {
               graphic = vbox {
                   label(it.metaData.name)
                   label(it.cardCount.toString())
               }
               onDoubleClick {
                   fire(ChangeViewEvent(ViewPages.NewEditing(it)))
               }
           }
        }
    }
}