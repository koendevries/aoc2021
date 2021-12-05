package com.koendevries.aoc2021.extensions

fun List<String>.transpose() = if (isEmpty()) {
    emptyList()
} else {
    fold(init()) { acc, line ->
        acc.mapIndexed { index, s -> s.append(line[index]) }
    }.map(StringBuilder::toString)
}

private fun List<String>.init() = (0 until first().length)
    .map { StringBuilder() }
    .toList()