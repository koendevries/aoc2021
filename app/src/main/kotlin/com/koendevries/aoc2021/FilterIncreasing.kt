package com.koendevries.aoc2021

fun List<Int>.filterIncreasing() = drop(1)
    .filterIndexed { index, number -> number > this[index] }