package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readCrabPositions
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object TreacheryOfWhalesKtTest : ShouldSpec({
    context("example") {
        val crabPositions = readCrabPositions(ExampleInput(7))
        should("find min cost by distance") {
            minCost(crabPositions, ::simpleDistanceCostFunction) shouldBe 37
        }
        should("find min cost by summed distance") {
            minCost(crabPositions, ::summedDistanceCostFunction) shouldBe 168
        }
    }

    context("standard") {
        val crabPositions = readCrabPositions(StandardInput(7))
        should("find min cost by distance") {
            minCost(crabPositions, ::simpleDistanceCostFunction) shouldBe 355521
        }
        should("find min cost by summed distance") {
            minCost(crabPositions, ::summedDistanceCostFunction) shouldBe 100148777
        }
    }
})
