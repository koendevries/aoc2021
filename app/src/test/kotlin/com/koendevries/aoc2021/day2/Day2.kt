package com.koendevries.aoc2021.day2

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day2 {

    private val input = File(Assignment(2, Part.A))
        .readLines()
        .map(::submarineCommandOf)

    @Test
    fun `should solve 2a`() {
        input
            .fold(SubmarinePosition(0L, 0L, 0L), ::applySubmarineCommandA)
            .run { horizontalPosition * depth }
            .also(::println)
    }

    @Test
    fun `should solve 2b`() {
        input
            .fold(SubmarinePosition(0L, 0L, 0L), ::applySubmarineCommandB)
            .run { horizontalPosition * depth }
            .also(::println)
    }

}