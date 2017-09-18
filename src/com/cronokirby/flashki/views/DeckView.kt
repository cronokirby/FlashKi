package com.cronokirby.flashki.views

import com.cronokirby.flashki.controllers.DeckStore
import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import com.cronokirby.flashki.models.Deck
import tornadofx.*

class DeckView : View() {
    private val store: DeckStore by inject()

    override val root = vbox {
        button("Create a deck") {
            action {
                fire(ChangeViewEvent(ViewPages.NewEditing(Deck.empty())))
            }
        }
        listview(store.decks) {
           cellFormat {
               graphic = cache {
                   vbox {
                       label(it.metaData.name)
                       label("${it.cardCount} cards")
                   }
               }
               onDoubleClick {
                   fire(ChangeViewEvent(ViewPages.NewEditing(it)))
               }
           }
        }
    }
}