package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readUniqueSignalPatternLines
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object UniqueSignalPatternLineTest : ShouldSpec({
    context("standard") {
        val uniqueSignalPatternLines = readUniqueSignalPatternLines(StandardInput(8))
        should("count of easy digits") {
            countEasyDigits(uniqueSignalPatternLines) shouldBe 445
        }
        should("sum of all digits") {
            sumAll(uniqueSignalPatternLines) shouldBe 1043101
        }
    }

})
