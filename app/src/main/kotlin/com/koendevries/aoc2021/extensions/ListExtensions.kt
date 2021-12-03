package com.koendevries.aoc2021.extensions

fun List<String>.transpose() = if (isEmpty()) {
    emptyList()
} else {
    fold(List(first().length) { "" }) { acc, line ->
        acc.mapIndexed { index, s -> s + line[index] }
    }
}