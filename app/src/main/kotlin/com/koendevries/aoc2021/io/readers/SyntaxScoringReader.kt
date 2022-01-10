package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Chunks
import com.koendevries.aoc2021.solutions.CorruptedChunks
import com.koendevries.aoc2021.solutions.IncompleteChunks

fun readChunkLine(input: Input) = File(input)
    .readLines()
    .map(::chunks)

private fun chunks(line: String): Chunks = line.fold(IncompleteChunks(emptyList()) as Chunks) { acc, next ->
    when {
        acc is IncompleteChunks && openCharByClosingChar.values.contains(next) -> IncompleteChunks(listOf(next) + acc.incomplete)
        acc is IncompleteChunks && openCharByClosingChar[next] == acc.incomplete.firstOrNull() -> IncompleteChunks(acc.incomplete.drop(1))
        acc is CorruptedChunks -> acc
        else -> CorruptedChunks(next)
    }
}

private val openCharByClosingChar = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')