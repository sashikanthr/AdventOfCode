package com.adventofcode2022.day9

import kotlin.math.abs

var state = State()

fun main() {
    readDirections()
}

fun readDirections() {
    var line = readLine()
    while (line != null) {
        val directions = line.split(" ")
        updateState(directions[0], directions[1].toInt())
        line = readLine()
    }
    println("Unique Positions Visited ${state.uniqueTailMovements.size}")
}

fun updateState(s: String, length: Int) {
    when (s) {
        "R" -> updateState(state.head.first, length, moveRow = false, increment = true)
        "L" -> updateState(state.head.first, length, moveRow = false, increment = false)
        "U" -> updateState(length, state.head.second, moveRow = true, increment = true)
        "D" -> updateState(length, state.head.second, moveRow = true, increment = false)
    }
}

fun updateState(x: Int, y: Int, moveRow: Boolean, increment: Boolean) {
    when (moveRow) {
        false -> if (increment) {
            for (i in state.head.second+1..state.head.second+y) {
                updateHead(x, i)
            }
        } else {
            for (i in state.head.second-1 downTo state.head.second-y) {
                updateHead(x, i)
            }
        }
        true -> if(increment) {
            for (i in state.head.first+1..state.head.first+x) {
                updateHead(i,y)
            }
        } else {
            for (i in state.head.first-1 downTo state.head.first-x) {
                updateHead(i,y)
            }
        }

    }
}


fun updateHead(x: Int, y: Int) {
    state.previousHead = state.head
    state.head = Pair(x, y)
    updateTail()
}

fun updateTail() {
    val headX = state.head.first
    val headY = state.head.second
    val tailX = state.tail.first
    val tailY = state.tail.second

    if(!(abs(headX-tailX)<=1 && abs(headY-tailY)<=1)) {
        state.tail = when {
            headX == tailX -> when {headY > tailY -> Pair(tailX,tailY+1) else -> Pair(tailX,tailY-1)}
            headY == tailY -> when {headX > tailX -> Pair(tailX+1,tailY) else -> Pair(tailX-1,tailY)}
            else -> state.previousHead
        }
        state.uniqueTailMovements.add(state.tail)
    }
}


class State {
    var head = Pair(0, 0)
    var previousHead = Pair(0,0)
    var tail = Pair(0, 0)
    var uniqueTailMovements = mutableSetOf(tail)
}
