package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readChunkLine
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object SyntaxScoringKtTest : ShouldSpec({
    context("example") {
        val chunkLines = readChunkLine(ExampleInput(10))
        should("calculate total syntax error score of corrupted chunks") {
            totalSyntaxErrorScore(chunkLines) shouldBe 26397L
        }
        should("calculate middle score of incomplete chunks") {
            middleScore(chunkLines) shouldBe 288957L
        }
    }

    context("standard") {
        val chunkLines = readChunkLine(StandardInput(10))
        should("calculate total syntax error score of corrupted chunks") {
            totalSyntaxErrorScore(chunkLines) shouldBe 415953
        }
        should("calculate middle score of incomplete chunks") {
            middleScore(chunkLines) shouldBe 2292863731
        }
    }
})