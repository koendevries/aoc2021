package com.koendevries.aoc2021.day2

import com.koendevries.aoc2021.day2.SubmarineCommand.*

data class SubmarinePosition(val horizontalPosition: Long, val depth: Long, val aim: Long)

fun applyA(p: SubmarinePosition, c: SubmarineCommand) = when (c) {
    is Forward -> p.copy(horizontalPosition = p.horizontalPosition + c.amount)
    is Up -> p.copy(depth = p.depth - c.amount)
    is Down -> p.copy(depth = p.depth + c.amount)
}

fun applyB(p: SubmarinePosition, c: SubmarineCommand) = when (c) {
    is Forward -> p.copy(horizontalPosition = p.horizontalPosition + c.amount, depth = p.depth + p.aim * c.amount)
    is Up -> p.copy(aim = p.aim - c.amount)
    is Down -> p.copy(aim = p.aim + c.amount)
}