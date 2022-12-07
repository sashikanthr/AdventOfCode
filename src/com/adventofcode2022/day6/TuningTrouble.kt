package com.adventofcode2022.day6

//Part 1
// var numberOfDistinctChars = 4

//Part 2
var numberOfDistinctChars = 14

fun main() {
    readLine()?.let { println(processDataStream(it)) }
}

fun processDataStream(line: String): Int {
    for (i in 0..line.length) {
        val uniqueChars = mutableSetOf(line[i])
        var j = i + 1
        while (j < line.length && j < i + numberOfDistinctChars) {
            uniqueChars.add(line[j])
            j++
        }
        if (uniqueChars.size == numberOfDistinctChars) return i + numberOfDistinctChars
    }
    return line.length + 1
}