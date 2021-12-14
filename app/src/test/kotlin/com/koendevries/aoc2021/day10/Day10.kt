package com.koendevries.aoc2021.day10

import com.koendevries.aoc2021.collections.extensions.median
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day10 {
    private val lines = File(Assignment(10, Part.A)).readLines()

    private val openCharByClosingChar = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    private val pointsByIllegalChar = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val pointsByOpening = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)

    @Test
    fun `should solve 10a`() {
        lines.map(this::toChunkLine)
            .filterIsInstance<IllegalCharacter>()
            .map(IllegalCharacter::char)
            .sumOf(pointsByIllegalChar::getValue)
            .also(::println)
    }

    @Test
    fun `should solve 10b`() {
        lines.map(this::toChunkLine)
            .filterIsInstance<Incomplete>()
            .map(this::toScore)
            .run(Iterable<Long>::median)
            .also(::println)
    }

    private fun toScore(incomplete: Incomplete) = incomplete.incompletes.fold(0L) { acc, next -> 5 * acc + pointsByOpening.getOrDefault(next, 0) }


    private fun toChunkLine(line: String): ChunkLine = line.fold(Incomplete(emptyList()) as ChunkLine) { acc, next ->
        when {
            acc is Incomplete && openCharByClosingChar.values.contains(next) -> Incomplete(listOf(next) + acc.incompletes)
            acc is Incomplete && openCharByClosingChar[next] == acc.incompletes.firstOrNull() -> Incomplete(acc.incompletes.drop(1))
            acc is IllegalCharacter -> acc
            else -> IllegalCharacter(next)
        }
    }
}

private sealed interface ChunkLine
private data class IllegalCharacter(val char: Char) : ChunkLine
private data class Incomplete(val incompletes: List<Char>) : ChunkLine
