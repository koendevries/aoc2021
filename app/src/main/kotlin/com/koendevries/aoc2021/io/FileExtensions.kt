package com.koendevries.aoc2021.io

import com.koendevries.aoc2021.io.util.AocInput
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.io.util.InputExample
import java.io.File

fun File(aocInput: AocInput) = File(
    when (aocInput) {
        is Input -> "src/test/resources/2021/${aocInput.day}.txt"
        is InputExample -> "src/test/resources/2021/example/${aocInput.day}.txt"
    }
)