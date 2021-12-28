package com.koendevries.aoc2021.day13

import com.koendevries.aoc2021.geo.Coordinate
import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day13 {

    val input = File(Assignment(13, Part.A)).readText()
        .split("\n\n")
        .let { (dots, instructions) -> readDots(dots) to readInstructions(instructions) }

    @Test
    fun `should fold once`() {
        input.let { (dots, instructions) -> fold(dots, instructions.first()) }
            .count()
            .also(::println)
    }

    @Test
    fun `should fold all`() {
        generateSequence(input) { (dots, instructions) -> fold(dots, instructions) }
            .take(input.second.size + 1)
            .last()
            .run { print(first) }
    }

    private fun print(coordinates: Set<Coordinate>) = (0..coordinates.maxOf(Coordinate::y))
        .forEach { y ->
            (0..coordinates.maxOf(Coordinate::x))
                .forEach { x -> if (coordinates.contains(Coordinate(x, y))) print("#") else print(" ") }
                .also { println() }
        }

    private fun fold(dots: Set<Coordinate>, instructions: List<Instruction>) = Pair(
        fold(dots, instructions.first()),
        instructions.drop(1)
    )

    private fun fold(dots: Set<Coordinate>, instruction: Instruction): Set<Coordinate> = when (instruction) {
        is HorizontalFold -> dots.mapNotNull { foldHorizontal(it, instruction) }.toSet()
        is VerticalFold -> dots.mapNotNull { foldVertical(it, instruction) }.toSet()
    }

    private fun foldHorizontal(dot: Coordinate, instruction: HorizontalFold): Coordinate? = when {
        dot.x < instruction.position -> dot
        dot.x > instruction.position -> dot.copy(x = instruction.position.minus(dot.x - instruction.position))
        else -> null
    }

    private fun foldVertical(dot: Coordinate, instruction: VerticalFold): Coordinate? = when {
        dot.y < instruction.position -> dot
        dot.y > instruction.position -> dot.copy(y = instruction.position.minus(dot.y - instruction.position))
        else -> null
    }

    private fun readInstructions(lines: String) = lines.lines()
        .map(::readInstruction)

    private fun readInstruction(line: String) = line.split(" ")
        .let(List<String>::last)
        .split("=")
        .let { (orientation, position) -> if (orientation == "x") HorizontalFold(position.toInt()) else VerticalFold(position.toInt()) }

    private fun readDots(lines: String) = lines.lines()
        .map(::readCoordinate)
        .toSet()

    private fun readCoordinate(line: String) = line.split(",")
        .map(String::toInt)
        .let { (x, y) -> Coordinate(x, y) }

}

sealed interface Instruction
data class HorizontalFold(val position: Int) : Instruction
data class VerticalFold(val position: Int) : Instruction