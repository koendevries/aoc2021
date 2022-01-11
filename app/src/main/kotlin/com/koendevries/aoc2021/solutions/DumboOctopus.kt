package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.Grid
import com.koendevries.aoc2021.collections.allNeighboursOf
import com.koendevries.aoc2021.geo.Point

typealias Energy = Int
typealias EnergyGrid = Grid<Energy>

private fun step(energyGrid: EnergyGrid) = energyGrid
    .mapValues { (_, energy) -> energy + 1 }
    .let { grid -> grid.withFlashes(flashingPoints(grid)) }
    .let { grid -> grid to grid.countFlashes() }

private fun flashingPoints(grid: Grid<Energy>) = grid.filter { (_, energy) -> energy > 9 }.keys.toList()

private fun EnergyGrid.withFlashes(flashes: List<Point>): EnergyGrid {
    val point = flashes.firstOrNull()
    val energy = point?.let(this::getValue)
    return when {
        energy == null -> this
        energy == 0 -> this.withFlashes(flashes.drop(1))
        energy < 9 -> this.plus(Pair(point, energy + 1)).withFlashes(flashes.drop(1))
        else -> this.plus(point to 0).withFlashes(flashes.drop(1) + allNeighboursOf(point))
    }
}

fun EnergyGrid.countFlashes() = values.count(0::equals)

fun countFlashesAfter(grid: EnergyGrid, steps: Int) = generateSequence(step(grid)) { (grid, _) -> step(grid) }
    .take(steps)
    .sumOf { (_, flashes) -> flashes }

fun countStepsTillSimultaneousFlash(grid: EnergyGrid) = generateSequence(grid to 0) { (grid, _) -> step(grid) }
    .takeWhile { (_, flashes) -> flashes != grid.size }
    .count()