package com.koendevries.aoc2021.day3

import com.koendevries.aoc2021.collections.extensions.transpose
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.StandardInput
import org.junit.Test

class Day3 {

    private val diagnosticReport = File(StandardInput(3))
        .readLines()

    @Test
    fun `should solve 3a`() {
        diagnosticReport
            .transpose()
            .map { Pair(occursMost(it), occursLeast(it)) }
            .fold(Pair("", "")) { acc, pair -> acc.copy(first = acc.first + pair.first, second = acc.second + pair.second) }
            .run { first.toInt(2) * second.toInt(2) }
            .also(::println)
    }

    @Test
    fun `should solve 3b`() {
        findRating(diagnosticReport, ::occursMost)
            .times(findRating(diagnosticReport, ::occursLeast))
            .also(::println)
    }

}


