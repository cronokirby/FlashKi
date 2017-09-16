package com.cronokirby.flashki.views

import tornadofx.*

class MainView : View() {
    val centerView: CenterPage by inject()

    override val root = borderpane {
        top = label("Hello Flashki")
        center = centerView.root
    }
}