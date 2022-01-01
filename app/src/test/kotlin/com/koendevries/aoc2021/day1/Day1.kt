package com.koendevries.aoc2021.day1

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import org.junit.Test


class Day1 {
    private val numbers = File(Input(1))
        .readLines()
        .map(String::toInt)

    @Test
    fun `should solve 1a`() {
        numbers.windowed(2)
            .count { it.last() > it.first() }
            .also(::println)
    }

    @Test
    fun `should solve 1b`() {
        numbers.windowed(4)
            .count { it.last() > it.first() }
            .also(::println)
    }

}