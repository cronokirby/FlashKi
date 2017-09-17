package com.cronokirby.flashki.events

import tornadofx.FXEvent

enum class ViewPages {
    Editing,
    NotEditing
}

class ChangeViewEvent(val page: ViewPages) : FXEvent()

