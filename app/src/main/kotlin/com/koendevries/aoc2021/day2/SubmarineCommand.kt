package com.koendevries.aoc2021.day2

import com.koendevries.aoc2021.day2.SubmarineCommand.*

sealed class SubmarineCommand(val amount: Long) {
    class Forward(amount: Long) : SubmarineCommand(amount)
    class Down(amount: Long) : SubmarineCommand(amount)
    class Up(amount: Long) : SubmarineCommand(amount)
}

fun String.toSubmarineCommand(): SubmarineCommand {
    val (name, amount) = split(" ").let { Pair(it.first(), it.last().toLong()) }
    return when (name) {
        "forward" -> Forward(amount)
        "down" -> Down(amount)
        "up" -> Up(amount)
        else -> throw IllegalArgumentException()
    }
}