package com.koendevries.aoc2021.collections.extensions

fun <T1, T2> Map<T1, T2>.swap(): Map<T2, T1> = entries.associate { Pair(it.value, it.key) }