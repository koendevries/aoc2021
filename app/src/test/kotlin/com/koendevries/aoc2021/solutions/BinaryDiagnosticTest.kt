package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readDiagnosticReport
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object BinaryDiagnosticTest : ShouldSpec({
    context("example") {
        val diagnosticReport = readDiagnosticReport(ExampleInput(3))
        should("calculate power consumption") {
            powerConsumption(diagnosticReport) shouldBe 198
        }
        should("calculate life support rating") {
            lifeSupportRating(diagnosticReport) shouldBe 230
        }
    }

    context("standard") {
        val diagnosticReport = readDiagnosticReport(StandardInput(3))
        should("calculate power consumption") {
            powerConsumption(diagnosticReport) shouldBe 3912944
        }
        should("calculate life support rating") {
            lifeSupportRating(diagnosticReport) shouldBe 4996233
        }
    }
})