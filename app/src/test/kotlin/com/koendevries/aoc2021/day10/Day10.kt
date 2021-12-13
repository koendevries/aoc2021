package com.koendevries.aoc2021.day10

import com.koendevries.aoc2021.collections.extensions.median
import com.koendevries.aoc2021.collections.extensions.swap
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test
import kotlin.math.max

class Day10 {
    private val lines = File(Assignment(10, Part.A)).readLines()
    private val openCharByClosingChar = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    private val closingCharByOpenChar = openCharByClosingChar.swap()
    private val pointsByIllegalChar = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val pointsByMissingChar = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    @Test
    fun `should solve 10a`() {
        lines.mapNotNull(::firstIllegalChar)
            .sumOf(pointsByIllegalChar::getValue)
            .also(::println)
    }

    @Test
    fun `should solve 10b`() {
        lines.filterNot(::hasIllegalChar)
            .map(::missingChars)
            .map(::toScore)
            .run(Iterable<Long>::median)
            .also(::println)
    }

    private fun toScore(missingChars: String, score: Long = 0L): Long = if (missingChars.isEmpty()) {
        score
    } else {
        toScore(
            missingChars = missingChars.drop(1),
            score = score * 5 + pointsByMissingChar[missingChars.first()]!!
        )
    }

    private fun missingChars(incompleteLine: String): String {
        val closings = openCharByClosingChar.keys
        val closing = incompleteLine.firstOrNull(closings::contains)
        return if (closing != null) {
            val closingIndex = incompleteLine.indexOf(closing)
            missingChars(incompleteLine.substring(0, closingIndex - 1) + incompleteLine.substring(closingIndex + 1))
        } else {
            incompleteLine
                .reversed()
                .map(closingCharByOpenChar::getValue)
                .joinToString("")
        }
    }

    private fun hasIllegalChar(line: String) = firstIllegalChar(line) != null

    private fun firstIllegalChar(line: String): Char? {
        val closings = openCharByClosingChar.keys
        val closing = line.firstOrNull(closings::contains)
        return if (closing == null) {
            null
        } else {
            val closingIndex = line.indexOfFirst(closings::contains)
            val openingIndex = max(0, closingIndex - 1)
            val previous = line[openingIndex]
            if (openCharByClosingChar[closing] == previous) {
                firstIllegalChar(line.substring(0, openingIndex) + line.substring(closingIndex + 1))
            } else {
                closing
            }
        }
    }
}