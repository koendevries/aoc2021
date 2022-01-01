package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readDiagnosticReport
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

object BinaryDiagnosticTest : ShouldSpec({

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