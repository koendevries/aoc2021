package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.BingoCard
import com.koendevries.aoc2021.solutions.BingoGame
import com.koendevries.aoc2021.solutions.BingoValue
import com.koendevries.aoc2021.solutions.Draws

fun readBingoGame(input: Input): BingoGame = File(input)
    .readText()
    .split("\n\n")
    .partition { it.contains(",") }
    .run { Pair(readDraws(first), second.map(::readCard)) }

fun readDraws(numbers: List<String>): Draws = numbers.first().split(",").map(String::toInt)

fun readCard(card: String): BingoCard = card
    .run { split("\n") }
    .map(String::trim)
    .map { it.split(Regex("\\s+")) }
    .map(::readBingoValue)

fun readBingoValue(row: List<String>) = row.map(String::toInt).map(::BingoValue)

