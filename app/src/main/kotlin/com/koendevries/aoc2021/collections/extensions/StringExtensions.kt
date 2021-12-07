package com.koendevries.aoc2021.collections.extensions

fun String.occurences(search: Char): Int = count { it == search }

fun String.indicesOf(search: Char): List<Int> = withIndex()
    .filter { search == it.value }
    .map(IndexedValue<Char>::index)