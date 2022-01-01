package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.columns
import com.koendevries.aoc2021.collections.extensions.indicesOf
import com.koendevries.aoc2021.collections.extensions.occurences

typealias Bit = Char
typealias BinaryNumber = String
typealias DiagnosticReport = List<BinaryNumber>

private inline fun rate(
    diagnosticReport: DiagnosticReport,
    readBit: (BinaryNumber) -> Bit
) = columns(diagnosticReport)
    .map(readBit)
    .joinToString("")
    .toInt(2)

private tailrec fun rating(
    diagnosticReport: DiagnosticReport,
    readBit: (BinaryNumber) -> Bit,
    index: Int = 0
): Int = if (diagnosticReport.size == 1) {
    diagnosticReport[0].toInt(2)
} else {
    val next = columns(diagnosticReport)
        .let { columns -> columns[index] }
        .let { column -> column to readBit(column) }
        .let { (column, bit) -> column.indicesOf(bit) }
        .let { indices -> diagnosticReport.filterIndexed { index, _ -> indices.contains(index) } }

    rating(next, readBit, index + 1)
}

private fun mostOccurences(line: String) = if (line.occurences('0') > line.occurences('1')) '0' else '1'
private fun leastOccurences(line: String) = if (line.occurences('0') <= line.occurences('1')) '0' else '1'

private fun epsilonRate(diagnosticReport: DiagnosticReport) = rate(diagnosticReport, ::leastOccurences)
private fun gammaRate(diagnosticReport: DiagnosticReport) = rate(diagnosticReport, ::mostOccurences)

private fun co2ScrubbingRating(diagnosticReport: DiagnosticReport) = rating(diagnosticReport, ::leastOccurences)
private fun oxygenGeneratorRating(diagnosticReport: DiagnosticReport) = rating(diagnosticReport, ::mostOccurences)

fun powerConsumption(diagnosticReport: DiagnosticReport) = gammaRate(diagnosticReport) * epsilonRate(diagnosticReport)
fun lifeSupportRating(diagnosticReport: DiagnosticReport) = oxygenGeneratorRating(diagnosticReport) * co2ScrubbingRating(diagnosticReport)
