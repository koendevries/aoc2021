package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.collections.gridOf
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Energy
import com.koendevries.aoc2021.solutions.EnergyGrid

fun readEnergyGrid(input: Input): EnergyGrid = File(input)
    .readLines()
    .map(::readEnergy)
    .let(::gridOf)

fun readEnergy(line: String): List<Energy> = line.map(Char::digitToInt)
