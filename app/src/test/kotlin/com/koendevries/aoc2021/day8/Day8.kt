package com.koendevries.aoc2021.day8

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import org.junit.Test

typealias UniqueSignalPattern = Set<Char>

data class Line(val definition: List<UniqueSignalPattern>, val output: List<UniqueSignalPattern>)

class Day8 {

    private val uniqueSizeByDigit = mapOf(1 to 2, 7 to 3, 4 to 4, 8 to 7)

    private val input = File(Input(8)).readLines().map(::readLine)

    private fun readLine(line: String) = line.split(" | ")
        .map { it.split(Regex("\\s+")) }
        .run { Line(readUniqueSignalPatterns(first()), readUniqueSignalPatterns(last())) }

    private fun readUniqueSignalPatterns(numbers: List<String>): List<UniqueSignalPattern> = numbers.map(String::toSet)

    @Test
    fun `should solve 8a`() {
        input.sumOf(::numberOfEasyDigits).also(::println)
    }

    @Test
    fun `should solve 8b`() {
        input.sumOf(::valueOf).also(::println)
    }

    private fun numberOfEasyDigits(line: Line) = line.output.count { uniqueSizeByDigit.values.contains(it.size) }

    private fun valueOf(line: Line): Int = line.output
        .map(digitByUniqueSignalPattern(line.definition)::getValue)
        .reduce { acc, i -> acc * 10 + i }

    private fun digitByUniqueSignalPattern(patterns: List<UniqueSignalPattern>) = (patterns - findEasyDigitByUniqueSignalPattern(patterns).values)
        .fold(findEasyDigitByUniqueSignalPattern(patterns)) { acc, current -> acc + findDigit(current, acc) }
        .entries
        .associate { (digit, uniqueSignalPattern) -> Pair(uniqueSignalPattern, digit) }

    private fun findEasyDigitByUniqueSignalPattern(definition: List<UniqueSignalPattern>) = uniqueSizeByDigit
        .entries
        .associate { (digit, size) -> Pair(digit, definition.find { it.size == size }!!) }

    private fun findDigit(pattern: UniqueSignalPattern, acc: Map<Int, UniqueSignalPattern>) = when (pattern.size) {
        5 -> when {
            pattern.containsAll(acc.getValue(7)) -> Pair(3, pattern)
            pattern.intersect(acc.getValue(4)).size == 3 -> Pair(5, pattern)
            else -> Pair(2, pattern)
        }
        6 -> when {
            pattern.containsAll(acc.getValue(4)) -> Pair(9, pattern)
            pattern.containsAll(acc.getValue(7)) -> Pair(0, pattern)
            else -> Pair(6, pattern)
        }
        else -> throw IllegalStateException()
    }
}