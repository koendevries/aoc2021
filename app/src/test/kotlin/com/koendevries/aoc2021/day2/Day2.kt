package com.koendevries.aoc2021.day2

import com.koendevries.aoc2021.Assignment
import com.koendevries.aoc2021.File
import com.koendevries.aoc2021.Part
import org.junit.Test

class Day2 {

    private val input = File(Assignment(2, Part.A))
        .readLines()
        .map(String::toSubmarineCommand)

    @Test
    fun `should solve 2a`() {
        input
            .fold(SubmarinePosition(0L, 0L, 0L), ::applyA)
            .run { horizontalPosition * depth }
            .also(::println)
    }

    @Test
    fun `should solve 2b`() {
        input
            .fold(SubmarinePosition(0L, 0L, 0L), ::applyB)
            .run { horizontalPosition * depth }
            .also(::println)
    }

}