package com.koendevries.aoc2021.io.util

sealed class AocInput(val day: Int)
class InputExample(day: Int) : AocInput(day)
class Input(day: Int) : AocInput(day)

