package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.geo.isUnidirectional
import com.koendevries.aoc2021.io.readers.readLineSegments
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object HydrothermalVentureTest : ShouldSpec({
    context("example") {
        val lineSegments = readLineSegments(ExampleInput(5))
        should("count horizontal and vertical overlaps") {
            crossingPoints(lineSegments, ::isUnidirectional) shouldBe 5
        }
        should("count all overlaps") {
            crossingPoints(lineSegments) shouldBe 12
        }
    }

    context("standard") {
        val lineSegments = readLineSegments(StandardInput(5))
        should("count horizontal and vertical overlaps") {
            crossingPoints(lineSegments, ::isUnidirectional) shouldBe 8622
        }
        should("count all overlaps") {
            crossingPoints(lineSegments) shouldBe 22037
        }
    }
})