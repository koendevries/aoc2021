package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.geo.Point
import com.koendevries.aoc2021.geo.between

typealias Coordinate = Point
typealias LineSegment = Pair<Coordinate, Coordinate>
typealias LineSegments = List<LineSegment>

fun crossingPoints(
    segments: LineSegments,
    lineSegmentFilter: (LineSegment) -> Boolean = { true }
) = segments
    .filter(lineSegmentFilter)
    .flatMap(::between)
    .groupBy { it }
    .count { it.value.size > 1 }