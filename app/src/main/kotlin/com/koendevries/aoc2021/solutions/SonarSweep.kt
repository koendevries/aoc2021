package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.AocInput
import java.io.File

private typealias SeaFloorDepth = Int
private typealias SonarSweepReport = List<SeaFloorDepth>

private fun File.readSonarSweepReport(): SonarSweepReport = readLines().map(String::toInt)

fun sonarSweep(input: AocInput, slidingWindowSize: Int) = File(input)
    .readSonarSweepReport()
    .windowed(slidingWindowSize)
    .count { lines -> lines.last() > lines.first() }


