package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.Grid
import com.koendevries.aoc2021.collections.columns
import com.koendevries.aoc2021.collections.indexOf
import com.koendevries.aoc2021.collections.rows

typealias Draw = Int
typealias Draws = List<Draw>
typealias BingoCards = List<BingoCard>
typealias BingoGame = Pair<Draws, BingoCards>

data class BingoCard(val values: Grid<Int>, val marked: Grid<Boolean>) {
    fun mark(draw: Draw) = values.indexOf(draw)
        ?.let { point -> this.copy(marked = marked + Pair(point, true)) }
        ?: this

    fun hasBingo() = marked.rows().any { row -> row.all { it } } || marked.columns().any { column -> column.all { it } }

    fun unmarkedSum() = marked.filterValues { marked -> !marked }.keys.sumOf { point -> values[point]!! }
}

private fun findBingo(draws: Draws, card: BingoCard): Pair<Draws, BingoCard> = if (card.hasBingo()) {
    Pair(emptyList(), card)
} else {
    val draw = draws.first()
    val (drawsTillBingo, bingoCard) = findBingo(draws.drop(1), card.mark(draw))
    Pair(listOf(draw) + drawsTillBingo, bingoCard)
}

private fun bingoScores(bingoGame: BingoGame) = bingoGame.second
    .map { card -> Pair(bingoGame.first, card) }
    .map { (draw, card) -> findBingo(draw, card) }

fun winningScore(bingoGame: BingoGame) = bingoScores(bingoGame)
    .minByOrNull { (draws, _) -> draws.size }
    ?.let { (draws, card) -> draws.last() * card.unmarkedSum() }

fun losingScore(bingoGame: BingoGame) = bingoScores(bingoGame)
    .maxByOrNull { (draws, _) -> draws.size }
    ?.let { (draws, card) -> draws.last() * card.unmarkedSum() }