package com.koendevries.aoc2021.day15

import com.koendevries.aoc2021.geo.Point
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test
import kotlin.math.min

class Day15 {

    private val input = File(Assignment(15, Part.A)).readLines()
        .map { row -> row.map { cost -> cost.digitToInt() } }
    private val directions = listOf(
        Point(-1, 0),
        Point(1, 0),
        Point(0, -1),
        Point(0, 1)
    )

    @Test
    fun `should solve 15a`() {
        val target = Point(input.first().size - 1, input.size - 1)
        val costs = input
            .flatMapIndexed { y, row -> row.mapIndexed { x, cost -> Point(x, y) to cost } }
            .associate { (point, cost) -> point to cost }

        generateSequence(State()) { state -> nextState(nextVisitingCosts(state, costs)) }
            .takeWhile { state -> !state.hasVisited(target) }.last()
            .also { state -> println(state.minByVisited[target]) }
    }

    private fun nextState(state: State): State {
        state.costByVisiting.entries
            .reduce() { acc, next -> if (acc.value < next.value) acc else next }
            .also { (point, cost) -> state.minByVisited[point] = cost }
            .also { (point, _) -> state.costByVisiting.remove(point) }
            .also { (point, _) -> state.current = point }
        return state
    }

    private fun nextVisitingCosts(state: State, costs: Map<Point, Int>): State {
        val (current, minByVisited, costByVisiting) = state
        return current.neighbours(directions)
            .map { point -> Pair(point, costs[point]?.plus(minByVisited[current]!!) ?: Int.MAX_VALUE) }
            .onEach { (point, cost) -> costByVisiting.merge(point, cost, ::min) }
            .let { state }
    }

    private data class State(
        var current: Point = Point(0, 0),
        val minByVisited: MutableMap<Point, Int> = mutableMapOf(Point(0, 0) to 0),
        val costByVisiting: MutableMap<Point, Int> = mutableMapOf()
    ) {
        fun hasVisited(point: Point) = minByVisited.keys.contains(point)
    }
}