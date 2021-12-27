package com.koendevries.aoc2021.day12

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day12 {

    private val connections = File(Assignment(12, Part.A))
        .readLines()
        .map(::readEdge)

    private val neighbours = connections.flatten()
        .distinct()
        .associateWith { key -> connections.filter { key in it }.flatten().filter { it != key } }

    @Test
    fun `should solve 12a`() {
        count().also(::println)
    }

    private fun count(visited: List<Vertex> = emptyList(), current: Vertex = START): Int = when (current) {
        is END -> 1
        in visited -> when (current) {
            is START -> 0
            is SmallCave -> 0
            else -> neighbours[current]!!.sumOf { neighbour -> count(visited + current, neighbour) }
        }
        else -> neighbours[current]!!.sumOf { neighbour -> count(visited + current, neighbour) }
    }


    private fun readEdge(line: String) = line.split("-").map(::readVertex)

    private fun readVertex(name: String) = when {
        name == "start" -> START
        name == "end" -> END
        name.all(Char::isUpperCase) -> BigCave(name)
        else -> SmallCave(name)
    }
}


sealed interface Vertex
object START : Vertex
object END : Vertex
data class SmallCave(val name: String) : Vertex
data class BigCave(val name: String) : Vertex

data class Edge(val source: Vertex, val destination: Vertex)