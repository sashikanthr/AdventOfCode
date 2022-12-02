package com.adventofcode2022.day2

enum class GAME {
    ROCK, PAPER, SCISSORS
}

enum class OPPONENT {
    A, B, C
}

enum class SELF {
    X, Y, Z
}

fun main() {
    var line = readLine()
    var score = 0
    var scoreII = 0
    while (line != null) {
        val chars: List<String> = line.split(" ")
        score += play(OPPONENT.valueOf(chars[0]), SELF.valueOf(chars[1]))
        scoreII += playII(OPPONENT.valueOf(chars[0]), SELF.valueOf(chars[1]))
        line = readLine()
    }
    println("Total Score: $score")
    println("Total Score II: $scoreII")
}

fun getOpponentGame(value: OPPONENT): GAME {
    return when (value) {
        OPPONENT.A -> GAME.ROCK
        OPPONENT.B -> GAME.PAPER
        OPPONENT.C -> GAME.SCISSORS
    }
}

fun getSelfGame(value: SELF): GAME {
    return when (value) {
        SELF.X -> GAME.ROCK
        SELF.Y -> GAME.PAPER
        SELF.Z -> GAME.SCISSORS
    }
}

fun getWinMove(value: GAME): GAME {
    return when (value) {
        GAME.ROCK -> GAME.PAPER
        GAME.PAPER -> GAME.SCISSORS
        GAME.SCISSORS -> GAME.ROCK
    }
}

fun getLoseMove(value: GAME): GAME {
    return when (value) {
        GAME.ROCK -> GAME.SCISSORS
        GAME.PAPER -> GAME.ROCK
        GAME.SCISSORS -> GAME.PAPER
    }
}

fun getShapeScore(value: GAME): Int {
    return when (value) {
        GAME.ROCK -> 1
        GAME.PAPER -> 2
        GAME.SCISSORS -> 3
    }
}

fun play(opp: OPPONENT, self: SELF): Int {
    val oppMove = getOpponentGame(opp)
    val selfMove = getSelfGame(self)
    return when (oppMove) {
        selfMove -> 3
        else -> {
            when (getWinMove(oppMove)) {
                selfMove -> 6
                else -> 0
            }
        }
    } + getShapeScore(selfMove)
}

//Part 2 of the problem
fun playII(opp: OPPONENT, self: SELF): Int {
    val oppMove = getOpponentGame(opp)
    return when (self) {
        SELF.X -> getShapeScore(getLoseMove(oppMove))
        SELF.Y -> return 3 + getShapeScore(oppMove)
        SELF.Z -> return 6 + getShapeScore(getWinMove(oppMove))
    }
}


