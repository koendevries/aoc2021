package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readSonarSweepReport
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object SonarSweepTest : ShouldSpec({
    context("examples") {
        val input = readSonarSweepReport(ExampleInput(1))
        should("solve first sonar sweep") {
            sonarSweep(input, 2) shouldBe 7
        }

        should("solve second sonar sweep") {
            sonarSweep(input, 4) shouldBe 5
        }
    }

    context("standard") {
        val input = readSonarSweepReport(StandardInput(1))
        should("solve first sonar sweep") {
            sonarSweep(input, 2) shouldBe 1832
        }

        should("solve second sonar sweep") {
            sonarSweep(input, 4) shouldBe 1858
        }
    }
})