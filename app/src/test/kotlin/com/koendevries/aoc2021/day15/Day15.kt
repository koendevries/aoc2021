package com.koendevries.aoc2021.day15

import com.koendevries.aoc2021.geo.Point
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import org.junit.Test
import java.util.*

private typealias Grid = Map<Point, Int>
private typealias WeightedPoint = Pair<Point, Int>

private fun Grid.neighbours(point: Point) = directions.map(point::plus).filter(this::contains)
private val directions = listOf(Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1))

class Day15 {

    private val input = File(Input(15)).readLines().map { row -> row.map { cost -> cost.digitToInt() } }
    private val source = Point(0, 0)
    private val grid: Grid = input.flatMapIndexed { y, row -> row.mapIndexed { x, cost -> Point(x, y) to cost } }.toMap()

    @Test
    fun `should solve 15a`() {
        val target = grid.keys.maxByOrNull { point -> point.x * point.y } ?: source
        println(shortestPath(source, target, grid))
    }

    @Test
    fun `should solve 15b`() {
        val expandedGrid = expand(grid, 5)
        val target = expandedGrid.keys.maxByOrNull { point -> point.x * point.y } ?: source
        println(shortestPath(source, target, expandedGrid))
    }

    private fun expand(grid: Grid, times: Int): Grid {
        val target = grid.keys.maxByOrNull { point -> point.x * point.y }!!
        val width = target.x + 1
        val height = target.y + 1

        return List(times) { x: Int ->
            List(times) { y ->
                grid.map { (point, weight) ->
                    WeightedPoint(
                        Point(
                            x = x * width + point.x,
                            y = y * height + point.y
                        ),
                        when (val amount = (weight + x + y) % 9) {
                            0 -> 9
                            else -> amount
                        }
                    )
                }
            }
        }.flatten().flatten().toMap()
    }

    private fun shortestPath(source: Point, target: Point, grid: Grid): Int {
        val weightedPoints = PriorityQueue<WeightedPoint> { p1, p2 -> p1.second - p2.second }.apply { offer(WeightedPoint(source, 0)) }
        val costSoFar = mutableMapOf(source to 0)

        while (weightedPoints.isNotEmpty()) {
            val (point, _) = weightedPoints.poll()

            for (neighbour in grid.neighbours(point)) {
                val weight = costSoFar[point]!! + grid[neighbour]!!
                if (neighbour !in costSoFar || costSoFar[neighbour]!! > weight) {
                    costSoFar[neighbour] = weight
                    weightedPoints.offer(WeightedPoint(neighbour, weight))
                }
            }
        }
        return costSoFar[target]!!
    }
}