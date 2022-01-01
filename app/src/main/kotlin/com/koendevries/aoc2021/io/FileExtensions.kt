package com.koendevries.aoc2021.io

import com.koendevries.aoc2021.io.util.ExampleInput
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.io.util.StandardInput
import java.io.File

fun File(input: Input) = File(
    when (input) {
        is StandardInput -> "src/test/resources/2021/${input.day}.txt"
        is ExampleInput -> "src/test/resources/2021/examples/${input.day}.txt"
    }
)