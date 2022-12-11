package com.adventofcode2022.day11

import java.util.*

val monkeyMap = mutableMapOf<Int, Monkey>()
var divisorMultiple = 1

fun main() {
    initializeMonkeys()
    playRounds(10000)
}

fun playRounds(numberOfRounds: Int) {
    for (round in 1..numberOfRounds) {
        monkeyMap.keys.sorted().forEach {
            if (monkeyMap.containsKey(it)) {
                inspectItems(monkeyMap[it]!!)
            }
        }
    }

    val inspectionCounters = monkeyMap.values.map { it.inspectionCounter.toLong() }.sortedDescending()
    println("Monkey Business ${inspectionCounters[0] * inspectionCounters[1]}")
}

fun inspectItems(monkey: Monkey) {

    val items = monkey.startingItems
    while (items.isNotEmpty()) {
        val value = items.poll()
        //Part I
        //val worryLevel = (monkey.operation.invoke(value)) / 3
        //Part II
        val worryLevel = (monkey.operation.invoke(value) % divisorMultiple)
        val transferToMonkey = monkeyMap[monkey.test.invoke(worryLevel)]
        transferToMonkey?.startingItems?.add(worryLevel)
        monkey.inspectionCounter++
    }

}

fun initializeMonkeys() {
    var line = readLine()
    var activeMonkey = Monkey(-1)
    var tempDivisibleBy = 0
    var tempTrueMonkey = -1
    var tempFalseMonkey = -1
    var canTestBeProcessed = false
    while (line != null) {
        if (line.trim().isNotEmpty()) {
            when {
                line.trim().startsWith("Monkey") -> activeMonkey = Monkey(line.replace("Monkey ", "")[0].digitToInt())
                line.trim().startsWith("Starting items") -> activeMonkey.startingItems.addAll(
                    line.trim().replace("Starting items: ", "").split(",").map { n -> n.trim().toLong() })
                line.trim().startsWith("Operation") -> {
                    val actionItems = line.trim().replace("Operation: ", "").split(" ")
                    activeMonkey.operation = {
                        val operand = if ("old" == actionItems[4]) it else actionItems[4].toLong()
                        when (actionItems[3]) {
                            "*" -> it * operand
                            "+" -> it + operand
                            "/" -> it / operand
                            else -> it - operand
                        }
                    }

                }
                line.trim().startsWith("Test: ") -> {
                    tempDivisibleBy = line.trim().replace("Test: ", "").split(" ")[2].toInt()
                    //Based on the input, directly multiplying the divisors. Ideally, this should be an LCM calculation
                    divisorMultiple *= tempDivisibleBy

                }
                line.trim().startsWith("If true") -> {
                    tempTrueMonkey = line.trim().replace("If true: ", "").split(" ")[3].toInt()
                }
                line.trim().startsWith("If false") -> {
                    tempFalseMonkey = line.trim().replace("If false: ", "").split(" ")[3].toInt()
                    canTestBeProcessed = true
                }
            }
            if (canTestBeProcessed) {
                val trueMonkey = tempTrueMonkey
                val falseMonkey = tempFalseMonkey
                val divisibleBy = tempDivisibleBy
                val testPredicate: (Long) -> Boolean = {
                    it % divisibleBy == 0L
                }
                activeMonkey.test = {
                    if (testPredicate.invoke(it)) {
                        trueMonkey
                    } else {
                        falseMonkey
                    }
                }
                canTestBeProcessed = false
                monkeyMap[activeMonkey.id] = activeMonkey

            }
        }
        line = readLine()
    }

}

data class Monkey(val id: Int) {
    var startingItems = LinkedList<Long>()
    lateinit var operation: (worryLevel: Long) -> Long
    lateinit var test: (Long) -> Int
    var inspectionCounter: Int = 0
}