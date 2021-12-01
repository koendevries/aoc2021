package com.koendevries.aoc2021.day1

import com.koendevries.aoc2021.Assignment
import com.koendevries.aoc2021.AssignmentPart
import com.koendevries.aoc2021.File
import org.junit.Test


class Day1 {
    private val numbers = File(Assignment(1, AssignmentPart.A))
        .readLines()
        .map(String::toInt)

    @Test
    fun `should solve 1a`() {
        numbers.windowed(2)
            .count { it[1] > it[0] }
            .also(::println)
    }

    @Test
    fun `should solve 1b`() {
        numbers.windowed(4)
            .count { it[3] > it[0] }
            .also(::println)
    }

}