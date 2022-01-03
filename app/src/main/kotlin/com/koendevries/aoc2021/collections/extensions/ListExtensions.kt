package com.koendevries.aoc2021.collections.extensions

fun columns(list: List<String>) = if (list.isEmpty()) {
    emptyList()
} else {
    list.fold(list.init()) { acc, line ->
        acc.mapIndexed { index, s -> s.append(line[index]) }
    }.map(StringBuilder::toString)
}

private fun List<String>.init() = (0 until first().length)
    .map { StringBuilder() }
    .toList()

@JvmName("columnsOfListGrid")
fun <T> columns(list: List<List<T>>) = if (list.isEmpty()) {
    emptyList()
} else {
    (0 until list.first().size).map { index -> list.map { it[index] } }
}
