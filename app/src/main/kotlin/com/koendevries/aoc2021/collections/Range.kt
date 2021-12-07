package com.koendevries.aoc2021.collections

fun rangeOf(values: List<Int>) = if (values.isEmpty()) 0..0 else values.minOf { it }..values.maxOf { it }
