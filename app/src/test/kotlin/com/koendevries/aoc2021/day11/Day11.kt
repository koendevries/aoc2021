package com.koendevries.aoc2021.day11

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part.A
import org.junit.Test

typealias Point = Pair<Int, Int>
typealias Grid = Map<Point, Int>

private val directions = sequenceOf(
    Point(-1, -1), Point(-1, 0), Point(-1, 1),
    Point(0, -1), Point(0, 1),
    Point(1, -1), Point(1, 0), Point(1, 1)
)

class Day11 {
    private val input: Map<Point, Int> = File(Assignment(11, A))
        .readLines()
        .flatMapIndexed() { y, row -> row.mapIndexed() { x, energy -> Pair(Point(x, y), energy.digitToInt()) } }
        .toMap()

    @Test
    fun `should solve 11a`() {
        generateSequence(next(input)) { next(it) }.take(100).sumOf(::flashes).also(::println)
    }

    @Test
    fun `should solve 11b`() {
        generateSequence(input) { next(it) }.takeWhile { flashes(it) != 100 }.count().also(::println)
    }

    private fun next(grid: Grid) = increaseFirstTodo(grid, grid.keys.asSequence(), emptySet())

    private fun increaseFirstTodo(grid: Grid, todo: Sequence<Point>, flashed: Set<Point>): Grid = when (val point = todo.firstOrNull()) {
        null -> grid
        else -> when {
            flashed.contains(point) -> increaseFirstTodo(grid, todo.drop(1), flashed)
            grid[point]!! < 9 -> increaseFirstTodo(
                grid + Pair(point, grid[point]!! + 1),
                todo.drop(1),
                flashed
            )
            else -> increaseFirstTodo(
                grid + Pair(point, 0),
                todo.drop(1) + neighbours(point, grid),
                flashed + point
            )
        }
    }

}

fun neighbours(point: Point, grid: Grid) = directions.map(point::plus).filter(grid::contains)
fun flashes(grid: Grid) = grid.values.count(0::equals)
operator fun Point.plus(other: Point) = Point(first + other.first, second + other.second)
