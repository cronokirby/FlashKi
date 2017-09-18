package com.cronokirby.flashki.views

import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import com.cronokirby.flashki.models.Deck
import tornadofx.*

class MainView : View() {
    val centerView: DeckView by inject()
    var editView: EditView = EditView(Deck.empty())

    override val root = borderpane {
        center = centerView.root
        subscribe<ChangeViewEvent> { event ->
            when (event.page) {
                is ViewPages.NewEditing -> {
                    editView = EditView(event.page.deck)
                    center.replaceWith(editView.root)
                }
                is ViewPages.NotEditing -> center.replaceWith(centerView.root)
            }
        }
    }
}