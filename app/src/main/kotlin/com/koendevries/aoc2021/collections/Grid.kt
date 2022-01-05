package com.koendevries.aoc2021.collections

import com.koendevries.aoc2021.geo.Point

// TODO: rewrite implementation with lists or arrays
typealias  Grid<T> = Map<Point, T>

fun <T> Grid<T>.columns() = entries.groupBy { (point, _) -> point.x }
    .values
    .map { column -> column.sortedBy { (point, _) -> point.y }.map { e -> e.value } }

fun <T> Grid<T>.rows() = entries.groupBy { (point, _) -> point.y }.values
    .map { row -> row.sortedBy { (point, _) -> point.x }.map { e -> e.value } }

fun <T> Grid<T>.indexOf(search: T) = entries.firstOrNull { (_, value) -> value == search }?.key

fun <T> gridOf(list: List<List<T>>) = list.flatMapIndexed { y, row ->
    row.mapIndexed { x, value ->
        Point(x, y) to value
    }
}.toMap()

fun <T> Grid<T>.toFalse() = entries.associate { (point, _) -> point to false }