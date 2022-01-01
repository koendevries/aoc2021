package com.koendevries.aoc2021.day9

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.StandardInput
import org.junit.Test

class Day9 {

    private val heightMap = File(StandardInput(9))
        .readLines()
        .map { line -> line.map(Char::digitToInt) }

    @Test
    fun `should solve 9a`() {
        lowPoints()
            .sumOf { (rowIndex, columnIndex) -> heightMap[rowIndex][columnIndex] + 1 }
            .also(::println)
    }

    @Test
    fun `should solve 9b`() {
        lowPoints()
            .map { position -> basin(position).size }
            .sortedDescending()
            .take(3)
            .reduce { acc, current -> acc * current }
            .also(::println)
    }

    private fun basin(position: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val height = heightMap[position.first][position.second]

        return neighbours(position)
            .filterNot { (x, y) -> heightMap[x][y] == 9 }
            .fold(setOf(position)) { acc, (x, y) ->
                if (heightMap[x][y] > height) {
                    acc + basin(Pair(x, y))
                } else {
                    acc
                }
            }
    }

    private fun lowPoints() = heightMap.indices
        .flatMap { x -> heightMap[x].indices.map { y -> Pair(x, y) } }
        .filter { position -> neighbours(position).all { (x, y) -> heightMap[x][y] > heightMap[position.first][position.second] } }

    private fun neighbours(position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (rowIndex, columnIndex) = position
        return setOf(
            Pair(rowIndex - 1, columnIndex),
            Pair(rowIndex + 1, columnIndex),
            Pair(rowIndex, columnIndex - 1),
            Pair(rowIndex, columnIndex + 1)
        )
            .filter { (row, column) -> row in heightMap.indices && column in heightMap.first().indices }
    }
}