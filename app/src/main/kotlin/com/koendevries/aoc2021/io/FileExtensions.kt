package com.koendevries.aoc2021.io

import com.koendevries.aoc2021.io.util.AocInput
import com.koendevries.aoc2021.io.util.Input
import com.koendevries.aoc2021.io.util.InputExample
import java.io.File

fun File(aocInput: AocInput): File {
    val name = when (aocInput) {
        is Input -> aocInput.day.toString()
        is InputExample -> aocInput.day.toString() + "example"
    }
    return File("src/test/resources/${name}.txt")
}