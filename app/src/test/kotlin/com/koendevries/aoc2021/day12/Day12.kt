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
        countSmallCaveOncePaths(listOf(Start)).also(::println)
    }

    @Test
    fun `should solve 12b`() {
        countSingleSmallCaveTwicePaths(listOf(Start)).also(::println)
    }

    private fun countSmallCaveOncePaths(visited: List<Cave>): Int = when (val current = visited.last()) {
        is End -> 1
        is BigCave -> countRemainingPath(visited)
        is Start -> when (visited.size) {
            1 -> countRemainingPath(visited)
            else -> 0
        }
        is SmallCave -> when (current) {
            in visited.dropLast(1) -> 0
            else -> countRemainingPath(visited)
        }
    }

    private fun countSingleSmallCaveTwicePaths(visited: List<Cave>): Int = when (val current = visited.last()) {
        is End -> 1
        is BigCave -> countRemainingPath(visited, ::countSingleSmallCaveTwicePaths)
        is Start -> when (visited.size) {
            1 -> countRemainingPath(visited, ::countSingleSmallCaveTwicePaths)
            else -> 0
        }
        is SmallCave -> when (current) {
            in visited.dropLast(1) -> countRemainingPath(visited, ::countSmallCaveOncePaths)
            else -> countRemainingPath(visited, ::countSingleSmallCaveTwicePaths)
        }
    }

    private fun countRemainingPath(visited: List<Cave>, countRemaining: (List<Cave>) -> Int = ::countSmallCaveOncePaths) =
        neighbours[visited.last()]!!.sumOf { neighbour -> countRemaining(visited + neighbour) }


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