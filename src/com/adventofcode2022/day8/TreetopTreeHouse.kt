package com.adventofcode2022.day8

var trees = mutableMapOf<String, Int>()
var maxRows = 0
var maxCols = 0

fun main() {
    readTrees()
    processTree()
}

fun processTree() {
    var counter = 0
    //Part II
    var maxScore = 0
    for (i in 0..maxRows) {
        for (j in 0..maxCols) {
            val key = "$i $j"
            when {
                i == 0 || j == 0 || j == maxCols || i == maxRows -> {
                    counter++
                }
                else -> {
                    if (trees[key]?.let { calculateVisibility(i, j, it) } == true) {
                        counter++
                    }
                    //Part II
                    val score = trees[key]?.let { calculateScenicScore(i,j, it) }
                    if (score != null) {
                        if(score > maxScore) {
                            maxScore = score
                        }
                    }
                }
            }
        }
    }

    println("Counter $counter")
    println("Max Score $maxScore")
}

fun calculateVisibility(row: Int, col: Int, height: Int): Boolean {
    return calculateRowVisibility(0, row - 1, col, height) || calculateRowVisibility(
        row + 1,
        maxRows,
        col,
        height
    ) || calculateColVisibility(0, col - 1, row, height) || calculateColVisibility(col + 1, maxCols, row, height)
}

fun calculateScenicScore(row: Int, col: Int, height: Int): Int {
    return calculateRowScoreUp(row - 1, 0, col, height) * calculateRowScoreDown(
        row + 1,
        maxRows,
        col,
        height
    ) * calculateColScoreLeft(col - 1, 0, row, height) * calculateColScoreRight(col + 1, maxCols, row, height)
}

fun calculateRowVisibility(startIndex: Int, endIndex: Int, col: Int, height: Int): Boolean {
    for (i in startIndex..endIndex) {
        val key = "$i $col"
        if (trees[key]!! >= height) {
            return false
        }
    }
    return true
}

fun calculateColVisibility(startIndex: Int, endIndex: Int, row: Int, height: Int): Boolean {
    for (i in startIndex..endIndex) {
        val key = "$row $i"
        if (trees[key]!! >= height) {
            return false
        }
    }
    return true
}

fun calculateRowScoreUp(startIndex: Int, endIndex: Int, col: Int, height: Int): Int {
    var rowScore = 0
    for (i in startIndex downTo  endIndex) {
        rowScore++
        val key = "$i $col"
        if (trees[key]!! >= height) {
            return rowScore
        }
    }
    return rowScore
}

fun calculateRowScoreDown(startIndex: Int, endIndex: Int, col: Int, height: Int): Int {
    var rowScore = 0
    for (i in startIndex..endIndex) {
        rowScore++
        val key = "$i $col"
        if (trees[key]!! >= height) {
            return rowScore
        }
    }
    return rowScore
}

fun calculateColScoreLeft(startIndex: Int, endIndex: Int, row: Int, height: Int): Int {
    var colScore = 0
    for (i in startIndex downTo endIndex) {
        colScore++
        val key = "$row $i"
        if (trees[key]!! >= height) {
            return colScore
        }
    }
    return colScore
}

fun calculateColScoreRight(startIndex: Int, endIndex: Int, row: Int, height: Int): Int {
    var colScore = 0
    for (i in startIndex..endIndex) {
        colScore++
        val key = "$row $i"
        if (trees[key]!! >= height) {
            return colScore
        }
    }
    return colScore
}


fun readTrees() {
    var row = 0
    var line = readLine()
    while (line != null) {
        for ((col, e) in line.toCharArray().withIndex()) {
            val key = "$row $col"
            trees[key] = e.digitToInt()
        }
        row++
        line = readLine()
    }
    maxRows = row - 1
    maxCols = (trees.size / row) - 1
}


