package com.cronokirby.flashki

import com.cronokirby.flashki.views.MainView
import javafx.application.Application
import tornadofx.App


class MainApp : App(MainView::class)

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java, *args)
}