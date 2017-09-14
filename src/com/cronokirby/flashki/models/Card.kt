package com.cronokirby.flashki.models

/**
 * Represents a single flashcard
 *
 * @param front the front side of the card
 * @param back the back side of the card
 */
class Card(val front: String, val back: String) {
    val id = front + back

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
}