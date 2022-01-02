package com.koendevries.aoc2021.solutions

typealias Draw = Int
typealias Draws = List<Draw>
typealias BingoCards = List<BingoCard>
typealias Bingo = Pair<Draws, BingoCards>

data class BingoValue(val value: Draw, val marked: Boolean = false) {
    fun mark(number: Int) = if (value == number) BingoValue(value, true) else this
}

data class BingoCard(val values: List<List<BingoValue>>) {
    fun mark(number: Int) = BingoCard(values.map { row -> row.map { it.mark(number) } })
    fun hasBingo() = values.find(::isBingo) != null || transpose(values).find(::isBingo) != null
    fun unmarkedSum() = values.flatten().filterNot { it.marked }.sumOf(BingoValue::value)
}

private fun isBingo(line: List<BingoValue>) = line.all(BingoValue::marked)

private fun transpose(list: List<List<BingoValue>>) = (0 until list.first().size)
    .map { index -> list.map { it[index] } }

private fun bingo(numbers: List<Int>, card: BingoCard): Pair<Draws, BingoCard> = if (card.hasBingo()) {
    Pair(emptyList(), card)
} else {
    val draw = numbers.first()
    val next = bingo(numbers.drop(1), card.mark(draw))
    Pair(listOf(draw) + next.first, next.second)
}

private fun bingoScores(bingo: Bingo) = bingo.second
    .map { card -> Pair(bingo.first, card) }
    .map { (draw, card) -> bingo(draw, card) }

fun winningScore(bingo: Bingo) = bingoScores(bingo).minByOrNull { it.first.size }?.run { first.last() * second.unmarkedSum() }

fun losingScore(bingo: Bingo) = bingoScores(bingo).maxByOrNull { it.first.size }?.run { first.last() * second.unmarkedSum() }