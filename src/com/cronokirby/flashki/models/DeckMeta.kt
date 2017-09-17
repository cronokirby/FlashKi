package com.cronokirby.flashki.models

import javafx.beans.property.SimpleStringProperty
import tornadofx.getValue
import tornadofx.setValue

class DeckMeta(category: String, name: String) {
    val categoryProperty = SimpleStringProperty(category)
    var category by categoryProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty
}
