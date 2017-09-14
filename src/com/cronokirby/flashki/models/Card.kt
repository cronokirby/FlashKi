package com.cronokirby.flashki.models

class Card(val front: String, val back: String) {
    val id = front + back

    fun isThat(that: Card): Boolean {
        return this.id == that.id
    }
}