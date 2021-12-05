package com.koendevries.aoc2021.day4

data class BingoCard(val values: List<List<BingoValue>>) {

    fun mark(number: Int) = BingoCard(values.map { row -> row.map { it.mark(number) } })

    fun hasBingo() = values.find(::isBingo) != null || transpose(values).find(::isBingo) != null

    fun unmarkedSum() = values.flatten().filterNot { it.marked }.sumOf(BingoValue::value)

}

private fun isBingo(line: List<BingoValue>) = line.all(BingoValue::marked)

private fun transpose(list: List<List<BingoValue>>) = (0 until list.first().size)
    .map { index -> list.map { it[index] } }

