package com.koendevries.aoc2021.io.util

sealed class Input(val day: Int)
class ExampleInput(day: Int) : Input(day)
class StandardInput(day: Int) : Input(day)

