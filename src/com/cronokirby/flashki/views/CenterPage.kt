package com.cronokirby.flashki.views

import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import tornadofx.View
import tornadofx.action
import tornadofx.button

class CenterPage : View() {
    override val root = button("Create a deck") {
        action {
            fire(ChangeViewEvent(ViewPages.Editing))
        }
    }

}