package com.koendevries.aoc2021.day12

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day12 {

    private val connections = File(Assignment(12, Part.A))
        .readLines()
        .map(::readConnection)

    private val neighbours = connections.flatten()
        .distinct()
        .associateWith { key -> connections.filter { key in it }.flatten().filter { it != key } }

    @Test
    fun `should solve 12a`() {
        println(pathsVisitingSmallCavesOnce())
    }

    @Test
    fun `hould solve 12b`() {
        println(pathsVisitingSmallCaveTwice())
    }

    private fun pathsVisitingSmallCavesOnce(current: Cave = Start, visited: List<Cave> = emptyList()): Int = when (current) {
        is End -> 1
        is BigCave -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCavesOnce(neighbour, visited + current) }
        is Start -> when {
            visited.isEmpty() -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCavesOnce(neighbour, visited + current) }
            else -> 0
        }
        is SmallCave -> when (current) {
            in visited -> 0
            else -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCavesOnce(neighbour, visited + current) }
        }
    }

    private fun pathsVisitingSmallCaveTwice(current: Cave = Start, visited: List<Cave> = emptyList()): Int = when (current) {
        is End -> 1
        is BigCave -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCaveTwice(neighbour, visited + current) }
        is Start -> when {
            visited.isEmpty() -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCaveTwice(neighbour, visited + current) }
            else -> 0
        }
        is SmallCave -> when (current) {
            in visited -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCavesOnce(neighbour, visited + current) }
            else -> neighbours[current]!!.sumOf { neighbour -> pathsVisitingSmallCaveTwice(neighbour, visited + current) }
        }
    }


    private fun readConnection(line: String) = line.split("-").map(::readCave)

    private fun readCave(name: String) = when {
        name == "start" -> Start
        name == "end" -> End
        name.all(Char::isUpperCase) -> BigCave(name)
        else -> SmallCave(name)
    }
}

private sealed interface Cave
private object Start : Cave
private object End : Cave
private data class SmallCave(val name: String) : Cave
private data class BigCave(val name: String) : Cave