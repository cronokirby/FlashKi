package com.cronokirby.flashki.models

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Represents a single flashcard
 *
 * @param front the front side of the card
 * @param back the back side of the card
 */
class Card(front: String, back: String) {
    val id
        get() = front + back

    val frontProperty = SimpleStringProperty(front)
    var front by frontProperty

    val backProperty = SimpleStringProperty(back)
    var back by backProperty

    /**
     * Returns whether or not this card is equivalent to another
     *
     * Returns true if the card's id is the same. The id is simply
     * the concatenation of the front and the back atm, but this may change
     * in the future.
     *
     * @param that the card to compare to
     */
    fun isThat(that: Card): Boolean {
        return this.id == that.id
    }

    override fun toString(): String {
        return "Card(front=$front, back=$back)"
    }


}

class CardModel(property: ObjectProperty<Card>) : ItemViewModel<Card>(itemProperty = property) {
    val front = bind(autocommit = true) { item?.frontProperty }
    val back = bind(autocommit = true) { item?.backProperty }
}