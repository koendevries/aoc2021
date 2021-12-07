package com.koendevries.aoc2021.day7

import com.koendevries.aoc2021.collections.rangeOf
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test
import kotlin.math.absoluteValue

class Day7 {

    private val positions = File(Assignment(7, Part.A))
        .readText()
        .split(",")
        .map(String::toInt)

    @Test
    fun `should solve 7a`() {
        rangeOf(positions)
            .fold(listOf<Int>()) { acc, position -> acc + positions.sumOf { it.minus(position).absoluteValue } }
            .minOf { it }
            .also(::println)
    }

    @Test
    fun `should solve 7b`() {
        rangeOf(positions)
            .fold(listOf<Int>()) { acc, position -> acc + positions.sumOf { amountOfFuel(it, position) } }
            .minOf { it }
            .also(::println)
    }

}

private fun amountOfFuel(from: Int, to: Int): Int = 1
    .rangeTo(from.minus(to).absoluteValue)
    .fold(0, Int::plus)
