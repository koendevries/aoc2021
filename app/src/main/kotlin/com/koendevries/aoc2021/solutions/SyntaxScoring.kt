package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.median

sealed interface Chunks
object CorrectChunks : Chunks
data class IncompleteChunks(val incomplete: List<Char>) : Chunks
data class CorruptedChunks(val firstIllegalChar: Char) : Chunks

private fun CorruptedChunks.syntaxErrorScore() = when (firstIllegalChar) {
    ')' -> 3L
    ']' -> 57L
    '}' -> 1197L
    '>' -> 25137L
    else -> throw IllegalStateException()
}

private fun IncompleteChunks.score() = incomplete.foldRight(0L, ::addIncomplete)

private fun addIncomplete(opening: Char, score: Long): Long = 5 * score + when (opening) {
    '(' -> 1L
    '[' -> 2L
    '{' -> 3L
    '<' -> 4L
    else -> 0L
}

fun totalSyntaxErrorScore(lines: List<Chunks>): Long = lines
    .filterIsInstance<CorruptedChunks>()
    .sumOf(CorruptedChunks::syntaxErrorScore)

fun middleScore(lines: List<Chunks>): Long = lines
    .filterIsInstance<IncompleteChunks>()
    .map(IncompleteChunks::score)
    .run(Iterable<Long>::median)
