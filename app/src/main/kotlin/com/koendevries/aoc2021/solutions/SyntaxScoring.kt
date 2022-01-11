package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.median

sealed interface Chunks
object Complete : Chunks
data class Incomplete(val opened: List<OpeningChar>) : Chunks
data class Corrupt(val firstIllegalChar: ClosingChar) : Chunks

sealed interface ChunkChar
data class OpeningChar(val char: Char, val matches: Char, val score: Long): ChunkChar
data class ClosingChar(val char: Char, val matches: Char, val score: Long): ChunkChar

private fun Corrupt.syntaxErrorScore() = firstIllegalChar.score

private fun Incomplete.score() = opened.foldRight(0L, ::addIncomplete)

private fun addIncomplete(opening: OpeningChar, score: Long): Long = 5 * score + opening.score

fun totalSyntaxErrorScore(lines: List<Chunks>): Long = lines
    .filterIsInstance<Corrupt>()
    .sumOf(Corrupt::syntaxErrorScore)

fun middleScore(lines: List<Chunks>): Long = lines
    .filterIsInstance<Incomplete>()
    .map(Incomplete::score)
    .run(Iterable<Long>::median)
