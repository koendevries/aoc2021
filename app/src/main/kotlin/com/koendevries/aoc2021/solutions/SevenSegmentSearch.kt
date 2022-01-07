package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.swap
import com.koendevries.aoc2021.io.readers.*

fun countEasyDigits(entries: Entries) = entries
    .flatMap(Entry::output)
    .filterIsInstance<EasyPattern>()
    .count()

fun sumOfFourDigitOutputValue(entries: Entries): Int = entries.sumOf(::toFourDigitOutputValue)

fun toFourDigitOutputValue(entry: Entry): Int {
    val decoder: Map<Pattern, Int> = decoderOf(entry.patterns)

    return entry.output
        .map(decoder::getValue)
        .reduce { acc, i -> acc * 10 + i }
}

fun decoderOf(patterns: List<Pattern>): Map<Pattern, Int> {
    val easy = patterns
        .filterIsInstance<EasyPattern>()
        .associate(::decode)

    val hard = patterns
        .filterIsInstance<HardPattern>()
        .associate { pattern -> decode(pattern, easy.swap()) }

    return easy + hard
}

fun decode(pattern: EasyPattern) = when (pattern.segments.size) {
    2 -> pattern to 1
    3 -> pattern to 7
    4 -> pattern to 4
    7 -> pattern to 8
    else -> throw IllegalStateException()
}

fun decode(pattern: HardPattern, decoded: Map<Int, EasyPattern>) = when (pattern.segments.size) {
    5 -> when {
        pattern.segments.containsAll(decoded.getValue(7).segments) -> pattern to 3
        pattern.segments.intersect(decoded.getValue(4).segments).size == 3 -> pattern to 5
        else -> pattern to 2
    }
    6 -> when {
        pattern.segments.containsAll(decoded.getValue(4).segments) -> pattern to 9
        pattern.segments.containsAll(decoded.getValue(7).segments) -> pattern to 0
        else -> pattern to 6
    }
    else -> throw IllegalStateException()
}