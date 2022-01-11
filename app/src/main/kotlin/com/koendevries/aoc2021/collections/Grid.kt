package com.koendevries.aoc2021.collections

import com.koendevries.aoc2021.geo.Point

typealias  Grid<T> = Map<Point, T>

fun <T> gridOf(list: List<List<T>>) = list.flatMapIndexed { y, row ->
    row.mapIndexed { x, value ->
        Point(x, y) to value
    }
}.toMap()

fun <T> Grid<T>.columns() = entries.groupBy { (point, _) -> point.x }
    .values
    .map { column -> column.sortedBy { (point, _) -> point.y }.map { e -> e.value } }

fun <T> Grid<T>.rows() = entries.groupBy { (point, _) -> point.y }.values
    .map { row -> row.sortedBy { (point, _) -> point.x }.map { e -> e.value } }

fun <T> Grid<T>.indexOf(search: T) = entries.firstOrNull { (_, value) -> value == search }?.key

fun <T> Grid<T>.toFalse() = entries.associate { (point, _) -> point to false }


fun <T> Grid<T>.straightNeighboursOf(point: Point): List<Point> = STRAIGHT_NEIGHBOURS
    .map(point::plus)
    .filter(this::containsKey)


fun <T> Grid<T>.allNeighboursOf(point: Point): List<Point> = STRAIGHT_NEIGHBOURS
    .plus(DIAGONAL_NEIGHBOURS)
    .map(point::plus)
    .filter(this::containsKey)

val STRAIGHT_NEIGHBOURS = setOf(
    Point(-1, 0), Point(0, -1), Point(0, 1), Point(1, 0)
)

val DIAGONAL_NEIGHBOURS = setOf(
    Point(-1, -1), Point(-1, 1), Point(1, -1), Point(1, 1)
)
