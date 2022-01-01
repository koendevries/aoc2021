package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.AocInput
import java.io.File

private typealias SeaFloorDepth = Int
private typealias SonarSweepReport = List<SeaFloorDepth>

private fun sonarSweepReportOf(file: File): SonarSweepReport = file.readLines().map(String::toInt)

fun sonarSweep(input: AocInput, slidingWindowSize: Int) = sonarSweepReportOf(File(input))
    .windowed(slidingWindowSize)
    .count { lines -> lines.last() > lines.first() }


