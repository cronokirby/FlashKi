package com.cronokirby.flashki.events

import com.cronokirby.flashki.models.Deck
import tornadofx.FXEvent

open class ViewPages private constructor() {
    class Editing(val deck: Deck) : ViewPages()
    class NotEditing : ViewPages()
}

class ChangeViewEvent(val page: ViewPages) : FXEvent()

