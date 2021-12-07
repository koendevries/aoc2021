package com.koendevries.aoc2021.day3

import com.koendevries.aoc2021.collections.extensions.indicesOf
import com.koendevries.aoc2021.collections.extensions.occurences
import com.koendevries.aoc2021.collections.extensions.transpose

tailrec fun findRating(report: List<String>, bitCriteria: (s: String) -> Char, index: Int = 0): Int {
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