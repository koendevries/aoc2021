package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.UniqueSignalPattern
import com.koendevries.aoc2021.solutions.UniqueSignalPatternLine
import com.koendevries.aoc2021.solutions.UniqueSignalPatternLines

fun readUniqueSignalPatternLines(input: Input): UniqueSignalPatternLines = File(input)
    .readLines()
    .map(::readUniqueSignalPatternLine)

private fun readUniqueSignalPatternLine(line: String) = line
    .split(" | ")
    .map { uniqueSignalPatterns -> uniqueSignalPatterns.split(Regex("\\s+")) }
    .run { UniqueSignalPatternLine(readUniqueSignalPatterns(first()), readUniqueSignalPatterns(last())) }

private fun readUniqueSignalPatterns(patterns: List<String>): List<UniqueSignalPattern> = patterns.map(String::toSet)