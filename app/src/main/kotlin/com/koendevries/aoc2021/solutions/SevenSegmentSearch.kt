package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.swap

typealias Entries = List<Entry>

data class Entry(val patterns: Patterns, val output: Output)
typealias Patterns = List<Pattern>
typealias Output = List<Pattern>
typealias Segments = Set<Segment>
typealias Segment = Char

sealed interface Pattern
data class EasyPattern(val segments: Segments) : Pattern
data class HardPattern(val segments: Segments) : Pattern

fun HardPattern.containsAll(other: EasyPattern) = segments.containsAll(other.segments)
fun HardPattern.intersect(other: EasyPattern) = segments.intersect(other.segments)

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

fun decoderOf(patterns: List<Pattern>): Map<Pattern, Int> = patterns
    .filterIsInstance<EasyPattern>()
    .associate(::decode)
    .let { easy ->
        easy + patterns
            .filterIsInstance<HardPattern>()
            .associate { pattern -> decode(pattern, easy.swap()) }
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
        pattern.containsAll(decoded.getValue(7)) -> pattern to 3
        pattern.intersect(decoded.getValue(4)).size == 3 -> pattern to 5
        else -> pattern to 2
    }
    6 -> when {
        pattern.containsAll(decoded.getValue(4)) -> pattern to 9
        pattern.containsAll(decoded.getValue(7)) -> pattern to 0
        else -> pattern to 6
    }
    else -> throw IllegalStateException()
}