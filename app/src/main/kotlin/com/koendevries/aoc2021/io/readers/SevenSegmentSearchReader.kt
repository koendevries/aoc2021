package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.*

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