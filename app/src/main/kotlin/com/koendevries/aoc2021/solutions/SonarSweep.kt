package com.koendevries.aoc2021.solutions

typealias SonarSweepReport = List<SeaFloorDepth>
typealias SeaFloorDepth = Int

fun sonarSweep(sonarSweepReport: SonarSweepReport, slidingWindowSize: Int) = sonarSweepReport
    .windowed(slidingWindowSize)
    .count { lines -> lines.last() > lines.first() }


