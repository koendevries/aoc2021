package com.koendevries.aoc2021.solutions

typealias InternalTimer = Long
typealias Lanternfishes = List<InternalTimer>
typealias Cache<K, V> = MutableMap<K, V>

const val DEFAULT_REPRODUCTION_DAYS = 7L
const val INITIAL_REPRODUCTION_DAYS = 9L

private fun familySizeAfter(days: Long, familySizeAfterDaysCache: Cache<Long, Long>): Long = familySizeAfterDaysCache.getOrPut(days) {
    if (days < 1) {
        1L
    } else {
        familySizeAfter(days - DEFAULT_REPRODUCTION_DAYS, familySizeAfterDaysCache)
        +familySizeAfter(days - INITIAL_REPRODUCTION_DAYS, familySizeAfterDaysCache)
    }
}

fun Lanternfishes.count(days: Int): Long {
    val familySizeAfterDaysCache = mutableMapOf(0L to 1L)
    return sumOf { internalTimer -> familySizeAfter(days - internalTimer, familySizeAfterDaysCache) }
}