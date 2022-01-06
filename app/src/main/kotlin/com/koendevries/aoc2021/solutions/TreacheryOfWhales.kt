package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.rangeOf
import kotlin.math.abs

typealias CrabPosition = Int
typealias CrabPositions = List<CrabPosition>

typealias Cost = Int
typealias CostFunction = (CrabPosition, CrabPosition) -> Cost

private fun CrabPosition.totalCost(
    crabPositions: CrabPositions,
    costFunction: CostFunction
) = crabPositions.sumOf { currentPosition -> costFunction(this, currentPosition) }

fun minCost(
    crabPositions: CrabPositions,
    costFunction: CostFunction
) = rangeOf(crabPositions)
    .fold(listOf<Cost>()) { costs, position -> costs + position.totalCost(crabPositions, costFunction) }
    .minOf { totalCost -> totalCost }

fun simpleDistanceCostFunction(from: CrabPosition, to: CrabPosition) = abs(from - to)

fun summedDistanceCostFunction(from: CrabPosition, to: CrabPosition): Cost = 1
    .rangeTo(abs(from - to))
    .fold(0, Int::plus)