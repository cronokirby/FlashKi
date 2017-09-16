package com.cronokirby.flashki.views

import tornadofx.View
import tornadofx.action
import tornadofx.button

class CenterPage : View() {
    override val root = button("Create a deck") {
        action {
            replaceWith(EditView::class)
        }
    }

}