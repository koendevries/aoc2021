package com.koendevries.aoc2021.day11

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import org.junit.Test

private typealias Point = Pair<Int, Int>
private typealias EnergyGrid = Map<Point, Int>

private val directions = listOf(
    Point(-1, -1), Point(-1, 0), Point(-1, 1),
    Point(0, -1), Point(0, 1),
    Point(1, -1), Point(1, 0), Point(1, 1)
)

class Day11 {
    private val input: Map<Point, Int> = File(Input(11))
        .readLines()
        .flatMapIndexed() { y, row -> row.mapIndexed() { x, energy -> Pair(Point(x, y), energy.digitToInt()) } }
        .toMap()

    @Test
    fun `should solve 11a`() {
        generateSequence(nextGrid(input)) { (grid, _) -> nextGrid(grid) }
            .take(100)
            .sumOf { (_, flashes) -> flashes }
            .also(::println)
    }

    @Test
    fun `should solve 11b`() {
        generateSequence(input to 0) { (grid, _) -> nextGrid(grid) }
            .takeWhile { (_, flashes) -> flashes != 100 }
            .count()
            .also(::println)
    }
}

private fun nextGrid(grid: EnergyGrid) = grid
    .mapValues { (_, energy) -> energy + 1 }
    .let { g -> handleFlashes(g, g.filter { (_, energy) -> energy > 9 }.keys.toList()) }
    .let { g -> Pair(g, flashes(g)) }

private fun handleFlashes(grid: EnergyGrid, todos: List<Point>): EnergyGrid = when {
    todos.isEmpty() -> grid
    else -> handleTodos(grid, todos)
}

private fun handleTodos(grid: EnergyGrid, todos: List<Point>): EnergyGrid {
    val point = todos.first()
    val energy = grid[point]!!
    return when {
        energy == 0 -> handleFlashes(grid = grid, todos = todos.drop(1))
        energy < 9 -> handleFlashes(grid = grid + Pair(point, energy + 1), todos = todos.drop(1))
        else -> handleFlashes(grid = grid + Pair(point, 0), todos = todos.drop(1) + neighbours(point, grid))
    }
}

fun neighbours(point: Point, grid: EnergyGrid) = directions.map(point::plus).filter(grid::contains)
fun flashes(grid: EnergyGrid) = grid.values.count(0::equals)
private operator fun Point.plus(other: Point) = Point(first + other.first, second + other.second)
