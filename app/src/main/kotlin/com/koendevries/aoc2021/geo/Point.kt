package com.koendevries.aoc2021.geo

import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    fun neighbours(directions: List<Point>): List<Point> = directions.map(this::plus)
}

fun Point.until(other: Point): List<Point> = between(Pair(this, other))

fun between(edge: Pair<Point, Point>): List<Point> {
    val step = step(edge)
    return (0 until countSteps(edge))
        .map { step }
        .fold(listOf(edge.first)) { acc, coordinate -> acc + listOf(acc.last() + coordinate) }
}

fun difference(edge: Pair<Point, Point>): Point = edge.run { second - first }

fun isUnidirectional(edge: Pair<Point, Point>): Boolean = difference(edge).run { x == 0 || y == 0 }


private fun countSteps(edge: Pair<Point, Point>) = difference(edge).run { if (x != 0) x.absoluteValue else y.absoluteValue }

private fun step(edge: Pair<Point, Point>) = difference(edge).run { Point(step(x), step(y)) }

private fun step(n: Int) = when {
    n < 0 -> -1
    n == 0 -> 0
    else -> 1
}

