package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.CrabPositions

fun readCrabPositions(input: Input): CrabPositions = File(input)
    .readText()
    .split(",")
    .map(String::toInt)