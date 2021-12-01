package com.koendevries.aoc2021

import java.io.File

data class Assignment(val day: Int, val part: Part) {
    override fun toString(): String = "$day${part.toString().lowercase()}"
}

enum class Part { A, B }

fun File(assignment: Assignment) = File("src/test/resources/${assignment}.txt")
