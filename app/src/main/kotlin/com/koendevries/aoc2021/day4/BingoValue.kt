package com.koendevries.aoc2021.day4

data class BingoValue(val value: Int, val marked: Boolean = false) {
    fun mark(number: Int) = if (value == number) BingoValue(value, true) else this
}
