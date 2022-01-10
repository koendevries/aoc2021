package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.readers.readHeightMap
import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.StandardInput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal object SmokingBasinKtTest : ShouldSpec({
    context("example") {
        val heightMap = readHeightMap(ExampleInput(9))
        should("sum of the risk levels of all low points on your heightmap") {
            sumOfLowPointsRiskLevel(heightMap) shouldBe 15
        }
        should("multiply together the sizes of the three largest basins") {
            multiplyLargestBasinSizes(heightMap, 3) shouldBe 1134
        }
    }

    context("standard") {
        val heightMap = readHeightMap(StandardInput(9))
        should("sum of the risk levels of all low points on your heightmap") {
            sumOfLowPointsRiskLevel(heightMap) shouldBe 417
        }
        should("multiply together the sizes of the three largest basins") {
            multiplyLargestBasinSizes(heightMap, 3) shouldBe 1148965
        }
    }
})