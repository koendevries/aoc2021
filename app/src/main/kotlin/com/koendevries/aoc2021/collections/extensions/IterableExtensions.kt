package com.koendevries.aoc2021.collections.extensions

fun Iterable<Long>.median() = sorted().run {
    when {
        size == 0 -> throw NoSuchElementException()
        size % 2 == 0 -> get(size.minus(1) / 2)
        else -> get(size / 2)
    }
}