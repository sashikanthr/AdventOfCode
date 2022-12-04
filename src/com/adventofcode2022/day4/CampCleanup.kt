package com.adventofcode2022.day4

fun main() {
    var line = readLine()
    var counter = 0
    while (line != null) {
        val pairs = line.split(",")
        val pair1 = pairs[0].split("-")
        val pair2 = pairs[1].split("-")
        if (checkForOverlap(pair1, pair2)) {
            counter++
        }
        line = readLine()
    }
    println("Number of pairs : $counter")
}

fun checkIfPairContainsAnother(pair1: List<String>, pair2: List<String>): Boolean {
    val sec1 = pair1[0].toInt()
    val sec2 = pair1[1].toInt()
    val sec3 = pair2[0].toInt()
    val sec4 = pair2[1].toInt()
    return when {
        sec1 >= sec3 && sec2 <= sec4 -> true
        sec3 >= sec1 && sec4 <= sec2 -> true
        else -> false
    }
}

//Part II
fun checkForOverlap(pair1: List<String>, pair2: List<String>): Boolean {
    val sec1 = pair1[0].toInt()
    val sec2 = pair1[1].toInt()
    val sec3 = pair2[0].toInt()
    val sec4 = pair2[1].toInt()
    return when {

        sec1 < sec3 && sec1 < sec4 && sec2 < sec3 && sec2 < sec4 -> false
        sec3 < sec1 && sec3 < sec2 && sec4 < sec1 && sec4 < sec2 -> false
        else -> true
    }
}

