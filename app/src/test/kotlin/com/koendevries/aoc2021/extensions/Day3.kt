package com.koendevries.aoc2021.extensions

import com.koendevries.aoc2021.Assignment
import com.koendevries.aoc2021.File
import com.koendevries.aoc2021.Part
import org.junit.Test

class Day3 {

    private val diagnosticReport = File(Assignment(3, Part.A))
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

fun findRating(report: List<String>, bitCriteria: (s: String) -> Char, index: Int = 0): Int {
    if (report.size == 1) {
        return report[0].toInt(2)
    }
    val rowIndicesToStay = report.transpose()[index]
        .run { indicesOf(bitCriteria(this)) }

    return findRating(
        report.filterIndexed { i, _ -> rowIndicesToStay.contains(i) },
        bitCriteria,
        index + 1
    )
}

fun occursMost(line: String) = if (line.occurences('0') > line.occurences('1')) '0' else '1'
fun occursLeast(line: String) = if (line.occurences('0') <= line.occurences('1')) '0' else '1'
