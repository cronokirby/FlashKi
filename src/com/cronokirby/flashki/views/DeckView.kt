package com.cronokirby.flashki.views

import com.cronokirby.flashki.controllers.DeckStore
import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import tornadofx.*

class DeckView : View() {
    val store: DeckStore by inject()

    override val root = vbox {
        button("Create a deck") {
            action {
                fire(ChangeViewEvent(ViewPages.Editing))
            }
        }
        listview(store.decks) {
           cellFormat {
               graphic = cache {
                   vbox {
                       label(it.metaData.name)
                   }
               }
           }
        }
    }
}