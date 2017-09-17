package com.cronokirby.flashki.views

import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
import tornadofx.*

class MainView : View() {
    val centerView: CenterPage by inject()

    override val root = borderpane {
        top = label("Hello Flashki")
        center = centerView.root
        subscribe<ChangeViewEvent> { event ->
            when (event.page) {
                ViewPages.Editing -> center.replaceWith(EditView().root)
                ViewPages.NotEditing -> center.replaceWith(CenterPage().root)
            }
        }
    }
}