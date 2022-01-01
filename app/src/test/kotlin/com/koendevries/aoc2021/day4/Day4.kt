package com.koendevries.aoc2021.day4

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input
import org.junit.Test

class Day4 {

    private val bingo: Pair<List<Int>, List<BingoCard>> = File(Input(4))
        .readText()
        .split("\n\n")
        .partition { it.contains(",") }
        .run { Pair(readDraws(first), second.map(::readCard)) }

    @Test
    fun `should solve 4a`() {
        bingo.second
            .map { Pair(bingo.first, it) }
            .map { bingo(it.first, it.second) }
            .minByOrNull { it.first.size }
            ?.run { first.last() * second.unmarkedSum() }
            ?.also(::println)
    }

    @Test
    fun `should solve 4b`() {
        bingo.second
            .map { Pair(bingo.first, it) }
            .map { bingo(it.first, it.second) }
            .maxByOrNull { it.first.size }
            ?.run { first.last() * second.unmarkedSum() }
            ?.also(::println)
    }
}

fun bingo(numbers: List<Int>, card: BingoCard): Pair<List<Int>, BingoCard> {
    if (card.hasBingo()) {
        return Pair(emptyList(), card)
    }
    val draw = numbers.first()
    val next = bingo(numbers.drop(1), card.mark(draw))
    return Pair(listOf(draw) + next.first, next.second)
}

fun readCard(card: String) = card
    .run { split("\n") }
    .map(String::trim)
    .map { it.split(Regex("\\s+")) }
    .map(::asBingoValue)
    .let(::BingoCard)

fun readDraws(numbers: List<String>) = numbers.first().split(",").map(String::toInt)

fun asBingoValue(row: List<String>) = row.map(String::toInt).map(::BingoValue)