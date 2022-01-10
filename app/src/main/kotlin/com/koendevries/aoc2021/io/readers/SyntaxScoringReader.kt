package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Chunks
import com.koendevries.aoc2021.solutions.CorrectChunks
import com.koendevries.aoc2021.solutions.CorruptedChunks
import com.koendevries.aoc2021.solutions.IncompleteChunks

fun readChunkLine(input: Input) = File(input)
    .readLines()
    .map(::chunks)

private fun chunks(line: String): Chunks = line.fold(CorrectChunks as Chunks) { chunks, char ->
    when (chunks) {
        is CorrectChunks -> chunks.next(char)
        is IncompleteChunks -> chunks.next(char)
        is CorruptedChunks -> chunks
    }
}

fun CorrectChunks.next(char: Char) = when {
    isOpeningChar(char) -> IncompleteChunks(listOf(char))
    else -> CorruptedChunks(char)
}

fun IncompleteChunks.next(char: Char): Chunks = when {
    isOpeningChar(char) -> IncompleteChunks(incomplete + char)
    isCorrect(char) -> CorrectChunks
    isMatching(char) -> IncompleteChunks(incomplete.dropLast(1))
    else -> CorruptedChunks(char)
}

fun IncompleteChunks.isCorrect(next: Char) = incomplete.size == 1 && isMatching(next)
fun isOpeningChar(char: Char) = char in "([{<"
fun IncompleteChunks.isMatching(next: Char) = when (next) {
    ')' -> '(' == incomplete.lastOrNull()
    ']' -> '[' == incomplete.lastOrNull()
    '}' -> '{' == incomplete.lastOrNull()
    '>' -> '<' == incomplete.lastOrNull()
    else -> false
}