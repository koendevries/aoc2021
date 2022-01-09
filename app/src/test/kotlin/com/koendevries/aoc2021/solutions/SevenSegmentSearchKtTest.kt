package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readEntries
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object SevenSegmentSearchKtTest : ShouldSpec({
    context("example") {
        val entries = readEntries(ExampleInput(8))
        should("count of easy digits") {
            countEasyDigits(entries) shouldBe 26
        }
        should("sum of all four digit output values") {
            sumOfFourDigitOutputValue(entries) shouldBe 61229
        }
    }

    context("standard") {
        val entries = readEntries(StandardInput(8))
        should("count of easy digits") {
            countEasyDigits(entries) shouldBe 445
        }
        should("sum of all four digit output values") {
            sumOfFourDigitOutputValue(entries) shouldBe 1043101
        }
    }
})