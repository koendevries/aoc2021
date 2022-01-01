package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input

fun readSonarSweepReport(input: Input) = File(input).readLines().map(String::toInt)