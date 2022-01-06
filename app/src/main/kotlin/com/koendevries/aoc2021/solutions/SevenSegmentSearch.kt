package com.koendevries.aoc2021.solutions

typealias UniqueSignalPattern = Set<Char>
typealias UniqueSignalPatternLines = List<UniqueSignalPatternLine>

data class UniqueSignalPatternLine(
    val definition: List<UniqueSignalPattern>,
    val output: List<UniqueSignalPattern>
)

private val uniqueSizeByDigit = mapOf(1 to 2, 7 to 3, 4 to 4, 8 to 7)

private fun easyDigits(line: UniqueSignalPatternLine) = line.output
    .filter { uniqueSizeByDigit.values.contains(it.size) }

private fun valueOf(line: UniqueSignalPatternLine): Int = line.output
    .map(digitByUniqueSignalPattern(line.definition)::getValue)
    .reduce { acc, i -> acc * 10 + i }

private fun digitByUniqueSignalPattern(patterns: List<UniqueSignalPattern>): Map<UniqueSignalPattern, Int> =
    (patterns - findEasyDigitByUniqueSignalPattern(patterns).values)
        .fold(findEasyDigitByUniqueSignalPattern(patterns)) { acc, current -> acc + findDigit(current, acc) }
        .entries
        .associate { (digit, uniqueSignalPattern) -> Pair(uniqueSignalPattern, digit) }

private fun findEasyDigitByUniqueSignalPattern(definition: List<UniqueSignalPattern>): Map<Int, UniqueSignalPattern> = uniqueSizeByDigit
    .entries
    .associate { (digit, size) -> digit to definition.find { it.size == size }!! }

private fun findDigit(pattern: UniqueSignalPattern, acc: Map<Int, UniqueSignalPattern>): Pair<Int, UniqueSignalPattern> = when (pattern.size) {
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


fun countEasyDigits(lines: UniqueSignalPatternLines) = lines
    .map(::easyDigits)
    .sumOf(List<UniqueSignalPattern>::count)

fun sumAll(lines: UniqueSignalPatternLines) = lines.sumOf(::valueOf)