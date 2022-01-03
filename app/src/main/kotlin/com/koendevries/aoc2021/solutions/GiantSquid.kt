package com.koendevries.aoc2021.solutions

import com.koendevries.aoc2021.collections.extensions.columns

typealias Draw = Int
typealias Draws = List<Draw>
typealias BingoCard = List<List<BingoValue>>
typealias BingoCards = List<BingoCard>
typealias BingoGame = Pair<Draws, BingoCards>

data class BingoValue(val value: Draw, val marked: Boolean = false) {
    fun mark(number: Int) = if (value == number) BingoValue(value, true) else this
}

private fun BingoCard.mark(draw: Draw) = map { row -> row.map { it.mark(draw) } }
private fun BingoCard.hasBingo() = find(::allMarked) != null || columns(this).find(::allMarked) != null
private fun BingoCard.unmarkedSum() = flatten().filterNot(BingoValue::marked).sumOf(BingoValue::value)

private fun allMarked(line: List<BingoValue>) = line.all(BingoValue::marked)

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