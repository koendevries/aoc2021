package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.*
import java.lang.IllegalStateException

fun readChunkLine(input: Input) = File(input)
    .readLines()
    .map(::chunks)

private fun chunks(line: String): Chunks = line
    .map(::readChunkChar)
    .fold(Complete as Chunks) { chunks, char ->
        when (chunks) {
            is Complete -> chunks with char
            is Incomplete -> chunks with char
            is Corrupt -> chunks
        }
    }

fun readChunkChar(char: Char): ChunkChar = when (char) {
    '(' -> OpeningChar('(', ')', 1L)
    '[' -> OpeningChar('[', ']', 2L)
    '{' -> OpeningChar('{', '}', 3L)
    '<' -> OpeningChar('<', '>', 4L)
    ')' -> ClosingChar(')', '(', 3L)
    ']' -> ClosingChar(']', '[', 57L)
    '}' -> ClosingChar('}', '{', 1197L)
    '>' -> ClosingChar('>', '<', 25137L)
    else -> throw IllegalStateException()
}

infix fun Complete.with(char: ChunkChar) = when (char) {
    is OpeningChar -> Incomplete(listOf(char))
    is ClosingChar -> Corrupt(char)
}

infix fun Incomplete.with(char: ChunkChar): Chunks = when (char) {
    is OpeningChar -> Incomplete(opened + char)
    is ClosingChar -> when {
        completesWith(char) -> Complete
        matches(opened.lastOrNull(), char) -> Incomplete(opened.dropLast(1))
        else -> Corrupt(char)
    }
}

fun Incomplete.completesWith(next: ClosingChar) = opened.size == 1 && matches(opened.lastOrNull(), next)
fun matches(opening: OpeningChar?, closing: ClosingChar) = opening?.matches == closing.char