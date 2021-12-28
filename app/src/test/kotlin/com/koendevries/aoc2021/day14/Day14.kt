package com.koendevries.aoc2021.day14

import com.koendevries.aoc2021.io.Assignment
import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.Part
import org.junit.Test

class Day14 {

    private val input = File(Assignment(14, Part.A)).readText()
        .split("\n\n")
        .let { (template, rule) -> template to readRules(rule) }


    @Test
    fun `should solve 14a`() {
        val (template, rules) = input
        generateSequence(template) { t -> insert(t, rules) }
            .drop(10)
            .first()
            .let {
                val charToCount = it.toCharArray()
                    .distinct()
                    .associateWith { c -> it.toCharArray().count(c::equals) }
                it.maxOf(charToCount::getValue) - it.minOf(charToCount::getValue)
            }
            .also(::println)
    }

    private fun insert(template: String, rules: Map<String, String>) = template
        .windowed(2)
        .map { pair -> rules[pair] ?: pair }
        .reduce { acc, next -> acc.dropLast(1) + next }

    private fun readRules(text: String) = text.lines()
        .map(::readRule)
        .associate { (pair, toInsert) -> pair to (pair.first() + toInsert + pair.last()) }


    private fun readRule(line: String) = line.split(" -> ")
}
