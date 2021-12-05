package com.koendevries.aoc2021.geo

import kotlin.math.absoluteValue

data class Coordinate(val x: Int, val y: Int) {
    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
    operator fun minus(other: Coordinate) = Coordinate(x - other.x, y - other.y)
}

fun Coordinate.until(other: Coordinate) = between(Pair(this, other))

fun between(edge: Pair<Coordinate, Coordinate>): List<Coordinate> {
    val step = step(edge)
    return (0 until countSteps(edge))
        .map { step }
        .fold(listOf(edge.first)) { acc, coordinate -> acc + listOf(acc.last() + coordinate) }
}

fun difference(edge: Pair<Coordinate, Coordinate>) = edge.run { second - first }

fun isUnidirectional(edge: Pair<Coordinate, Coordinate>) = difference(edge).run { x == 0 || y == 0 }

private fun countSteps(edge: Pair<Coordinate, Coordinate>) = difference(edge).run { if (x != 0) x.absoluteValue else y.absoluteValue }

private fun step(edge: Pair<Coordinate, Coordinate>) = difference(edge).run { Coordinate(step(x), step(y)) }

private fun step(n: Int) = when {
    n < 0 -> -1
    n == 0 -> 0
    else -> 1
}
