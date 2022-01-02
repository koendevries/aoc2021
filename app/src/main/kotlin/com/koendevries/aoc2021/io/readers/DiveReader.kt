package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.solutions.Down
import com.koendevries.aoc2021.solutions.Forward
import com.koendevries.aoc2021.solutions.SubmarineCommand
import com.koendevries.aoc2021.solutions.Up

fun readSubmarineCommand(line: String): SubmarineCommand {
    val (name, amount) = line.split(" ").let { (name, amount) -> name to amount.toLong() }

    return when (name) {
        "forward" -> Forward(amount)
        "down" -> Down(amount)
        "up" -> Up(amount)
        else -> throw IllegalArgumentException()
    }
}

fun readSubmarineCommands(input: Input) = File(input).readLines().map(::readSubmarineCommand)