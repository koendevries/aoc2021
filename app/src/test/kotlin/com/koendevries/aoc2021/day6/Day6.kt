package com.koendevries.aoc2021.day6

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.StandardInput
import org.junit.Test

class Day6 {

    private val input = File(StandardInput(6))
        .readText()
        .split(",")
        .map(String::toLong)

    private val cache = mutableMapOf(0L to 1L)
    private fun familySizeAfter(days: Long): Long = cache.getOrPut(days) {
        if (days < 1) {
            1L
        } else {
            familySizeAfter(days - 7L) + familySizeAfter(days - 9L)
        }
    }

    @Test
    fun `should solve 6a`() {
        input.sumOf { familySizeAfter(80L - it) }.also(::println)
    }

    @Test
    fun `should solve 6b`() {
        input.sumOf { familySizeAfter(256L - it) }.also(::println)
    }
}