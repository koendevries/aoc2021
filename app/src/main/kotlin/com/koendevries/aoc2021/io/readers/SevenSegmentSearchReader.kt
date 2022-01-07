package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input

typealias Entries = List<Entry>

data class Entry(val patterns: Patterns, val output: Output)
typealias Patterns = List<Pattern>
typealias Output = List<Pattern>
typealias Segments = Set<Segment>
typealias Segment = Char

sealed interface Pattern
data class EasyPattern(val segments: Segments) : Pattern
data class HardPattern(val segments: Segments) : Pattern

fun readEntries(input: Input): Entries = File(input)
    .readLines()
    .map(::readEntry)

fun readEntry(line: String) = line
    .split(" | ")
    .map { pattern -> pattern.split(Regex("\\s+")) }
    .map(::readPatterns)
    .let { (patterns, output) -> Entry(patterns, output) }

fun readPatterns(patterns: List<String>): Patterns = patterns
    .map(String::toSet)
    .map(::toPattern)

fun toPattern(segments: Segments): Pattern = when (segments.size) {
    2, 3, 4, 7 -> EasyPattern(segments)
    else -> HardPattern(segments)
}