package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.collections.gridOf
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Height
import com.koendevries.aoc2021.solutions.HeightMap

fun readHeightMap(input: Input): HeightMap = File(input)
    .readLines()
    .map(::readHeight)
    .let(::gridOf)

private fun readHeight(line: String): List<Height> = line.map(Char::digitToInt)