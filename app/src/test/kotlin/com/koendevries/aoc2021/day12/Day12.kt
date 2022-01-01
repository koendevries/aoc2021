package com.koendevries.aoc2021.day12

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.StandardInput
import org.junit.Test

class Day12 {

    private val connections = File(StandardInput(12))
        .readLines()
        .map(::readConnection)

    private val neighbours = connections.flatten()
        .distinct()
        .associateWith { key -> connections.filter { key in it }.flatten().filter { it != key } }

    @Test
    fun `should solve 12a`() {
        countPaths(listOf(Start)).also(::println)
    }

    @Test
    fun `should solve 12b`() {
        countPaths(listOf(Start), ::countRemainingPath).also(::println)
    }

    private fun countPaths(visited: List<Cave>, countRemaining: (List<Cave>) -> Int = { 0 }): Int = when (val current = visited.last()) {
        is End -> 1
        is BigCave -> countRemainingPath(visited, countRemaining)
        is Start -> when (visited.size) {
            1 -> countRemainingPath(visited, countRemaining)
            else -> 0
        }
        is SmallCave -> when (current) {
            in visited.dropLast(1) -> countRemaining(visited)
            else -> countRemainingPath(visited, countRemaining)
        }
    }

    private fun countRemainingPath(visited: List<Cave>, countRemaining: (List<Cave>) -> Int = { 0 }) =
        neighbours[visited.last()]!!.sumOf { neighbour -> countPaths(visited + neighbour, countRemaining) }

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