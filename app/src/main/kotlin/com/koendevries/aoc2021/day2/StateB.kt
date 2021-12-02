package com.koendevries.aoc2021.day2

import com.koendevries.aoc2021.day2.SubmarineCommand.*

data class StateB(val horizontalPosition: Long, val depth: Long, val aim: Long) {
    fun apply(command: SubmarineCommand) = when (command) {
        is Forward -> copy(horizontalPosition = horizontalPosition + command.amount, depth = depth + depth * command.amount)
        is Up -> copy(depth = depth - command.amount)
        is Down -> copy(depth = depth + command.amount)
    }
}