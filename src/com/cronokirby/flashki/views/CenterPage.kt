package com.cronokirby.flashki.views

import com.cronokirby.flashki.controllers.DeckEditor
import com.cronokirby.flashki.models.Card
import com.cronokirby.flashki.models.Deck
import tornadofx.*

class CenterPage : View() {
    override val root = button("Create a deck") {
        action {
            replaceWith(EditView::class)
        }
    }

}