package com.adventofcode2022.day2

enum class GAME {
    ROCK {
        override fun win() = PAPER
        override fun lose() = SCISSORS
        override fun score() = 1
    }, PAPER {
        override fun win() = SCISSORS
        override fun lose() = ROCK
        override fun score() = 2
    }, SCISSORS {
        override fun win() = ROCK
        override fun lose() = PAPER
        override fun score() = 3
    };

    abstract fun win(): GAME
    abstract fun lose(): GAME
    abstract fun score(): Int
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

fun play(opp: OPPONENT, self: SELF): Int {
    val oppMove = getOpponentGame(opp)
    val selfMove = getSelfGame(self)
    return when (oppMove) {
        selfMove -> 3
        else -> {
            when (oppMove.win()) {
                selfMove -> 6
                else -> 0
            }
        }
    } + selfMove.score()
}

//Part 2 of the problem
fun playII(opp: OPPONENT, self: SELF): Int {
    val oppMove = getOpponentGame(opp)
    return when (self) {
        SELF.X -> (oppMove.lose()).score()
        SELF.Y -> return 3 + (oppMove).score()
        SELF.Z -> return 6 + (oppMove.win()).score()
    }
}


