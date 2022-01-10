package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.Grid
import com.koendevries.aoc2021.collections.neighboursOf
import com.koendevries.aoc2021.geo.Point

typealias Height = Int
typealias HeightMap = Grid<Height>

private fun HeightMap.basinOf(point: Point): Set<Point> = neighboursOf(point)
    .filterNot { neighbour -> getValue(neighbour) == 9 }
    .fold(setOf(point)) { total, neighbour ->
        if (getValue(neighbour) > getValue(point)) {
            total + basinOf(neighbour)
        } else {
            total
        }
    }

private fun lowPointsOf(heightMap: HeightMap) = heightMap.keys.filter(heightMap::hasLowPointAt)

private fun HeightMap.hasLowPointAt(
    point: Point
) = neighboursOf(point).all { neighbour -> getValue(neighbour) > getValue(point) }

fun sumOfLowPointsRiskLevel(heightMap: HeightMap) = lowPointsOf(heightMap)
    .sumOf { lowPoint -> heightMap.getValue(lowPoint) + 1 }

fun multiplyLargestBasinSizes(heightMap: HeightMap, numberOfBasins: Int) = lowPointsOf(heightMap).asSequence()
    .map(heightMap::basinOf)
    .map(Set<Point>::size)
    .sortedDescending()
    .take(numberOfBasins)
    .reduce(Int::times)