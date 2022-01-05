package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.geo.Point
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.LineSegment

fun readLineSegments(input: Input): List<LineSegment> = File(input)
    .readLines()
    .map(::readLineSegment)

private fun readLineSegment(line: String) = line.split(Regex("\\s+"))
    .run { Pair(readCoordinate(first()), readCoordinate(last())) }

private fun readCoordinate(line: String) = line.split(",")
    .run { Point(first().toInt(), last().toInt()) }