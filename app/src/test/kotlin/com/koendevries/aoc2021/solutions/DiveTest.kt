package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readSubmarineCommands
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object DiveTest : ShouldSpec({

    context("example") {
        val submarineCommands = readSubmarineCommands(ExampleInput(2))
        should("solve simple dive") {
            dive(submarineCommands, ::simpleSubmarineCommandHandler) shouldBe 150L
        }
        should("solve amingDive") {
            dive(submarineCommands, ::aimingSubmarineCommandHandler) shouldBe 900L
        }
    }

    context("standard") {
        val submarineCommands = readSubmarineCommands(StandardInput(2))
        should("solve simple dive") {
            dive(submarineCommands, ::simpleSubmarineCommandHandler) shouldBe 1714680L
        }
        should("solve aiming dive") {
            dive(submarineCommands, ::aimingSubmarineCommandHandler) shouldBe 1963088820L
        }
    }

})
