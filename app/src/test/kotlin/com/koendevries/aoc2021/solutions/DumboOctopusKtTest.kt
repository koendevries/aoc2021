package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readEnergyGrid
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object DumboOctopusKtTest : ShouldSpec({
    context("standard") {
        val energyGrid = readEnergyGrid(StandardInput(11))
        should("total flashes after 100 steps") {
            countFlashesAfter(energyGrid, 100) shouldBe 1785L
        }
        should("calculate first step during which all octopuses flash") {
            countStepsTillSimultaneousFlash(energyGrid) shouldBe 354L
        }
    }
})