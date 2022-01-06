package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readLanternfishes
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object LanternfishTest : ShouldSpec({
    context("example") {
        val lanternfishes = readLanternfishes(ExampleInput(6))
        should("count after 80 days") {
            lanternfishes.count(80) shouldBe 5934L
        }
        should("count after 256 days") {
            lanternfishes.count(256) shouldBe 26984457539L
        }
    }
    context("standard") {
        val lanternfishes = readLanternfishes(StandardInput(6))
        should("count after 80 days") {
            lanternfishes.count(80) shouldBe 350605L
        }
        should("count after 256 days") {
            lanternfishes.count(256) shouldBe 1592778185024L
        }
    }
})