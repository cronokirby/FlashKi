package com.cronokirby.flashki.models

class Category(vararg strings: String) {
    val paths = strings.toList()

    fun nextCategory(): Category? {
        val nextPaths = paths.drop(1)
        return if (nextPaths.isEmpty()) null else Category(*nextPaths.toTypedArray())
    }

    override fun toString(): String {
      return paths.joinToString("/")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (paths != other.paths) return false

        return true
    }

    override fun hashCode(): Int {
        return paths.hashCode()
    }
}