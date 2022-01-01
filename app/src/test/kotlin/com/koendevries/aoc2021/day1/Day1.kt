package com.koendevries.aoc2021.day1

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.io.util.InputExample
import com.koendevries.aoc2021.solutions.sonarSweep
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day1 : AnnotationSpec() {
    private val numbers = File(Input(1))
        .readLines()
        .map(String::toInt)

    @Test
    fun `should solve 1a`() {
        numbers.windowed(2)
            .count { it.last() > it.first() }
            .also(::println)
    }

    @Test
    fun `should solve 1b`() {
        numbers.windowed(4)
            .count { it.last() > it.first() }
            .also(::println)
    }

}


class SonarSweep : ShouldSpec({
    context("examples") {
        val input = InputExample(1)
        should("solve first sonar sweep") {
            sonarSweep(input, 2) shouldBe 7
        }

        should("solve second sonar sweep") {
            sonarSweep(input, 4) shouldBe 1858
        }
    }

    context("standard") {
        val input = Input(1)
        should("solve first sonar sweep") {
            sonarSweep(input, 2) shouldBe 1832
        }

        should("solve second sonar sweep") {
            sonarSweep(input, 4) shouldBe 1858
        }
    }
})