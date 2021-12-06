package com.koendevries.aoc2021.day6

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day6 {

    private val input = File(Assignment(6, Part.A))
        .readText()
        .split(",")
        .map(String::toLong)

    private val cache = mutableMapOf(0L to 1L)
    private fun familySizeAfter(days: Long): Long = cache.getOrPut(days) { if (days < 1) 1 else familySizeAfter(days - 7) + familySizeAfter(days - 9) }

    @Test
    fun `should solve 6a`() {
        input.sumOf { familySizeAfter(80 - it) }.also(::println)
    }

    @Test
    fun `should solve 6b`() {
        input.sumOf { familySizeAfter(256 - it) }.also(::println)
    }
}