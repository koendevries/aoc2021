package com.koendevries.aoc2021.day5

import com.koendevries.aoc2021.geo.Point
import com.koendevries.aoc2021.geo.between
import com.koendevries.aoc2021.geo.isUnidirectional
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day5 {

    private val edges = File(Assignment(5, Part.A))
        .readLines()
        .map(::readEdge)

    @Test
    fun `should solve 5a`() {
        edges
            .filter(::isUnidirectional)
            .flatMap(::between)
            .groupBy { it }
            .count { it.value.size > 1 }
            .also(::println)
    }

    @Test
    fun `should solve 5b`() {
        edges
            .flatMap(::between)
            .groupBy { it }
            .count { it.value.size > 1 }
            .also(::println)
    }
}

private fun readEdge(line: String) = line.split(Regex("\\s+"))
    .run { Pair(readCoordinate(first()), readCoordinate(last())) }

private fun readCoordinate(line: String) = line.split(",")
    .run { Point(first().toInt(), last().toInt()) }
