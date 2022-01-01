package com.koendevries.aoc2021.solutions

typealias SubmarineCommands = List<SubmarineCommand>
typealias SubmarineCommandHandler = (SubmarinePosition, SubmarineCommand) -> SubmarinePosition

data class SubmarinePosition(val horizontalPosition: Long, val depth: Long, val aim: Long)

sealed class SubmarineCommand(val amount: Long)
class Forward(amount: Long) : SubmarineCommand(amount)
class Down(amount: Long) : SubmarineCommand(amount)
class Up(amount: Long) : SubmarineCommand(amount)

fun simpleSubmarineCommandHandler(p: SubmarinePosition, c: SubmarineCommand) = when (c) {
    is Forward -> p.copy(horizontalPosition = p.horizontalPosition + c.amount)
    is Up -> p.copy(depth = p.depth - c.amount)
    is Down -> p.copy(depth = p.depth + c.amount)
}

fun aimingSubmarineCommandHandler(p: SubmarinePosition, c: SubmarineCommand) = when (c) {
    is Forward -> p.copy(horizontalPosition = p.horizontalPosition + c.amount, depth = p.depth + p.aim * c.amount)
    is Up -> p.copy(aim = p.aim - c.amount)
    is Down -> p.copy(aim = p.aim + c.amount)
}

fun dive(submarineCommands: SubmarineCommands, submarineCommandHandler: SubmarineCommandHandler) = submarineCommands
    .fold(SubmarinePosition(0L, 0L, 0L), submarineCommandHandler)
    .run { horizontalPosition * depth }