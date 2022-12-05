package com.adventofcode2022.day5

import java.util.*

val crateMap = mutableMapOf<Int, Stack<Char>>()

fun main() {
    getCurrentCrateAlignment()
    println(crateMap)
    fireCommands()
    println(crateMap)
    popTopmostBlock()
}

fun popTopmostBlock() {
    println(crateMap.values.map { c -> c.pop() }.joinToString().replace(",","").replace(" ",""))
}

fun fireCommands() {
    do {
        val command = readLine()
        val commandInput = command?.split(" ")
        commandInput?.let { moveBlocksII(commandInput[1].toInt(), commandInput[3].toInt(), commandInput[5].toInt()) }
    } while (command != null)
}

//Part I
fun moveBlocks(numberOfBlocks: Int, from: Int, to: Int) {
    for (i in numberOfBlocks downTo 1) {
        crateMap[to]?.push(crateMap[from]?.pop())
    }
}

//Part II
fun moveBlocksII(numberOfBlocks: Int, from: Int, to: Int) {
    val elements = Stack<Char>()
    for (i in numberOfBlocks downTo 1) {
        crateMap[from]?.pop()?.let { elements.add(it) }
    }
    while(elements.isNotEmpty()) {
        crateMap[to]?.push(elements.pop())
    }
}


fun getCurrentCrateAlignment() {
    var readAllCrates = false
    val crateLines = Stack<String>()
    while (!readAllCrates) {
        val line = readLine()
        when {
            line == null || line.isEmpty() -> readAllCrates = true
            else -> crateLines.push(line)
        }
    }
    initializeCrates(crateLines.pop())
    while (!crateLines.empty()) {
        processCrateRow(crateLines.pop())
    }
}

fun processCrateRow(line: String) {
    val elements = line.split(" ")
    var counter = 1
    var needsCounterUpdate = 0
    elements.forEach {
        when {
            it.trim().isEmpty() && needsCounterUpdate == 3 -> {
                counter++
                needsCounterUpdate = 0
            }
            it.trim().isNotEmpty() -> {
                crateMap[counter]?.push(it.trim()[1])
                needsCounterUpdate = 0
                counter++
            }
            else -> needsCounterUpdate++
        }
    }
}

fun initializeCrates(line: String) {
    val size = line.split("  ").size
    for (i in 1..size) {
        crateMap[i] = Stack<Char>()
    }
}

