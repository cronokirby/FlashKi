package com.cronokirby.flashki.models

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class DeckMeta(category: String, name: String) {
    val categoryProperty = SimpleStringProperty(category)
    var category by categoryProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty
}

class DeckMetaModel(property: ObjectProperty<DeckMeta>) : ItemViewModel<DeckMeta>(itemProperty = property) {
    val category = bind(autocommit = true) { item?.categoryProperty }
    val name = bind(autocommit = true) { item?.nameProperty }
}