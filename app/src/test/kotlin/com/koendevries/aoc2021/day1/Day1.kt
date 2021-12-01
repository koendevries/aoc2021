package com.koendevries.aoc2021.day1

import com.koendevries.aoc2021.*
import org.junit.Test


class Day1 {
    private val numbers = File(Assignment(1, AssignmentPart.A))
        .readLines()
        .map(String::toInt)

    @Test
    fun `should solve 1a`() {
        numbers.filterIncreasing()
            .count()
            .also(::println)
    }

    @Test
    fun `should solve 1b`() {
        numbers.windowed(3, 1, false, List<Int>::sum)
            .filterIncreasing()
            .count()
            .also(::println)
    }
}