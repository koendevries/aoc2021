package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.indicesOf
import com.koendevries.aoc2021.collections.extensions.occurences
import com.koendevries.aoc2021.collections.extensions.transpose

typealias Bit = Char
typealias BinaryNumber = String
typealias DiagnosticReport = List<BinaryNumber> // TODO: Implement with Grid

private inline fun rate(
    diagnosticReport: DiagnosticReport,
    readBit: (BinaryNumber) -> Bit
) = transpose(diagnosticReport)
    .map(readBit)
    .joinToString("")
    .toInt(2)

private tailrec fun rating(
    report: DiagnosticReport,
    readBit: (BinaryNumber) -> Bit,
    index: Int = 0
): Int = if (report.size == 1) {
    report[0].toInt(2)
} else {
    val next = transpose(report)
        .let { transposedReport -> transposedReport[index] }
        .let { column -> column to readBit(column) }
        .let { (column, bit) -> column.indicesOf(bit) }
        .let { indices -> report.filterIndexed { index, _ -> indices.contains(index) } }

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
