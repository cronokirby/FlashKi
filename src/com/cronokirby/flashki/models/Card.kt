package com.cronokirby.flashki.models

/**
 * Represents a single flashcard
 *
 * @param front the front side of the card
 * @param back the back side of the card
 */
data class Card(val front: String, val back: String) {
    val id = front + back

    fun isFull(): Boolean {
        return front != "" && back != ""
    }
}
