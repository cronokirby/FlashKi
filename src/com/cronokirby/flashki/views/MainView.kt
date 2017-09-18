package com.cronokirby.flashki.views

import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import com.cronokirby.flashki.models.Deck
import tornadofx.*

class MainView : View() {
    val deckView: DeckView by inject()
    var editView: EditView = EditView(Deck.empty())

    override val root = borderpane {
        center = deckView.root
        subscribe<ChangeViewEvent> { event ->
            when (event.page) {
                is ViewPages.NewEditing -> {
                    editView = EditView(event.page.deck)
                    center.replaceWith(editView.root)
                }
                is ViewPages.NotEditing -> center.replaceWith(deckView.root)
            }
        }
    }
}