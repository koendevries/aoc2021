package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Lanternfishes

fun readLanternfishes(input: Input): Lanternfishes = File(input)
    .readText()
    .split(",")
    .map(String::toLong)