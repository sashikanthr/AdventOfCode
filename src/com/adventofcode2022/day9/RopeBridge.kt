package com.adventofcode2022.day9

import kotlin.math.abs

var state = State()

fun main() {
    state.initializeTails(9,0,state.head)
    readDirections()
}

fun readDirections() {
    var line = readLine()
    while (line != null) {
        val directions = line.split(" ")
        updateState(directions[0], directions[1].toInt())
        line = readLine()
    }
    println("Unique Positions Visited ${state.tail.uniqueMovements.size}")
}

fun updateState(s: String, length: Int) {
    when (s) {
        "R" -> updateState(state.head.coordinates.first, length, moveRow = false, increment = true)
        "L" -> updateState(state.head.coordinates.first, length, moveRow = false, increment = false)
        "U" -> updateState(length, state.head.coordinates.second, moveRow = true, increment = true)
        "D" -> updateState(length, state.head.coordinates.second, moveRow = true, increment = false)
    }
}

fun updateState(x: Int, y: Int, moveRow: Boolean, increment: Boolean) {
    when (moveRow) {
        false -> if (increment) {
            for (i in state.head.coordinates.second + 1..state.head.coordinates.second + y) {
                updateHead(x, i)
            }
        } else {
            for (i in state.head.coordinates.second - 1 downTo state.head.coordinates.second - y) {
                updateHead(x, i)
            }
        }
        true -> if (increment) {
            for (i in state.head.coordinates.first + 1..state.head.coordinates.first + x) {
                updateHead(i, y)
            }
        } else {
            for (i in state.head.coordinates.first - 1 downTo state.head.coordinates.first - x) {
                updateHead(i, y)
            }
        }

    }
}


fun updateHead(x: Int, y: Int) {
    state.head.coordinates = Pair(x, y)
    state.head.uniqueMovements.add(state.head.coordinates)
    updateTail(state.head)
}

fun updateTail(head: Knot) {
    if (head.isTailInitialized()) {
        val headX = head.coordinates.first
        val headY = head.coordinates.second
        val tailX = head.tail.coordinates.first
        val tailY = head.tail.coordinates.second

        if (!(abs(headX - tailX) <= 1 && abs(headY - tailY) <= 1)) {
            head.tail.coordinates = when {
                headX == tailX -> when {
                    headY > tailY -> Pair(tailX, tailY + 1)
                    else -> Pair(tailX, tailY - 1)
                }
                headY == tailY -> when {
                    headX > tailX -> Pair(tailX + 1, tailY)
                    else -> Pair(tailX - 1, tailY)
                }
                else -> moveDiagonal(head)
            }
            head.tail.uniqueMovements.add(head.tail.coordinates)
        }
        updateTail(head.tail)
    }
}


fun moveDiagonal(head: Knot): Pair<Int, Int> {
    val x = when {
        head.coordinates.first > head.tail.coordinates.first -> head.tail.coordinates.first + 1
        else -> head.tail.coordinates.first - 1
    }
    val y = when {
        head.coordinates.second > head.tail.coordinates.second -> head.tail.coordinates.second + 1
        else -> head.tail.coordinates.second - 1
    }
    return Pair(x, y)
}


class State {
    var head = Knot()
    lateinit var tail:Knot

    fun initializeTails(maxTails:Int, counter:Int, knot:Knot) {
        if(counter<maxTails) {
            knot.tail = Knot()
            initializeTails(maxTails,counter+1,knot.tail)
        } else {
            tail = knot
        }
    }
}

class Knot {
    var coordinates = Pair(0, 0)
    var uniqueMovements = mutableSetOf(coordinates)
    lateinit var tail: Knot

    fun isTailInitialized(): Boolean {
        return this::tail.isInitialized
    }
}
