package com.koendevries.aoc2021.io.readers

import com.koendevries.aoc2021.io.File
import com.koendevries.aoc2021.io.util.Input

fun readDiagnosticReport(input: Input) = File(input).readLines()

fun readDiagnosticRep(input: Input) = File(input).readLines()
    .map(String::toCharArray)
    .toTypedArray()