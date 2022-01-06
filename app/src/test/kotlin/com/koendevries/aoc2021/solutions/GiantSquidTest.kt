package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readBingoGame
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object GiantSquidTest : ShouldSpec({
    context("example") {
        val bingo = readBingoGame(ExampleInput(4))
        should("return winning score") {
            winningScore(bingo) shouldBe 4512
        }
        should("return losing score") {
            losingScore(bingo) shouldBe 1924
        }
    }

    context("standard") {
        val bingo = readBingoGame(StandardInput(4))
        should("return winning score") {
            winningScore(bingo) shouldBe 2745
        }
        should("return losing score") {
            losingScore(bingo) shouldBe 6594
        }
    }
})