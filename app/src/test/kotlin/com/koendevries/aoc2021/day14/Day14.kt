package com.koendevries.aoc2021.day14

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.StandardInput
import org.junit.Test
import java.util.Collections.max
import java.util.Collections.min

private typealias PairCounter = Map<String, Long>
private typealias CharCounter = Map<Char, Long>
private typealias Rules = Map<String, Char>
private typealias State = Pair<PairCounter, CharCounter>

class Day14 {

    private val input = File(StandardInput(14)).readText()
        .split("\n\n")

    @Test
    fun `should solve 14a`() {
        charCounts(10).run { max(values) - min(values) }.also(::println)
    }

    @Test
    fun `should solve 14b`() {
        charCounts(40).run { max(values) - min(values) }.also(::println)
    }

    private fun charCounts(iterations: Int): CharCounter {
        val rules = input[1].lines().map { it.split(" -> ") }.associate { (k, v) -> Pair(k, v[0]) }
        val pairCounter = input[0].windowed(2).groupBy { it }.mapValues { (_, k) -> k.size.toLong() }
        val charCounter = input[0].groupBy { it }.mapValues { (_, k) -> k.size.toLong() }

        return generateSequence(Pair(pairCounter, charCounter)) { state -> nextState(state, rules) }
            .drop(iterations).first().second
    }

    private fun nextState(state: State, rules: Rules): State {
        val (pairCounter, charCounter) = state
        return pairCounter.entries
            .fold(
                Pair(
                    pairCounter.toMutableMap(),
                    charCounter.toMutableMap()
                )
            ) { (pairCounter, charCounter), (pair, count) ->
                val charToInsert = rules[pair]!!
                val left = pair.substring(0, 1) + charToInsert
                val right = charToInsert + pair.substring(1)

                pairCounter.merge(pair, count, Long::minus)
                pairCounter.merge(left, count, Long::plus)
                pairCounter.merge(right, count, Long::plus)
                charCounter.merge(charToInsert, count, Long::plus)

                Pair(pairCounter, charCounter)
            }
    }
}
