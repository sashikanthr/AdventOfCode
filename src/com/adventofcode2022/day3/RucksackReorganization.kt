package com.adventofcode2022.day3

fun main() {
    //Only one part can be verified at a time
    partI()
    partII()
}

fun partII() {
    var line = readLine()
    var prioritySum = 0
    var lines: MutableList<String> = mutableListOf()
    while(line!=null) {
        lines+=line
        if(lines.size == 3) {
            val matchedValue = getMatchingChar(lines[0], lines[1], lines[2])
            prioritySum+= getPriority(matchedValue)
            lines = mutableListOf()
        }
        line = readLine()
    }
    println("Priority Sum : $prioritySum")
}

fun partI() {
    var line = readLine()
    var prioritySum = 0
    while(line!=null) {
        val splitStrings = splitIntoTwo(line)
        val matchedValue = getMatchingChar(splitStrings[0],splitStrings[1])
        prioritySum+= getPriority(matchedValue)
        line = readLine()
    }
    println("Priority Sum : $prioritySum")
}

fun getPriority(charValue:Int):Int {
    return when {
        charValue >= 97 -> charValue - 96 // a -> z, ASCII 97 -> 122
        else -> charValue - 38 // A -> Z, ASCII 65 -> 90
    }
}

//Here we are processing an int stream. As the net result we need is an integer, not converting it back to char
fun getMatchingChar(str1:String, str2: String):Int {
    return str1.chars().filter{c1 -> str2.chars().anyMatch{c2 -> c1==c2}}.findFirst().asInt
}

fun getMatchingChar(str1:String, str2: String, str3: String):Int {
    return str1.chars().filter{c1 -> str2.chars().anyMatch{c2 -> c1==c2} && str3.chars().anyMatch{c3 -> c1==c3}}.findFirst().asInt
}

fun splitIntoTwo(line:String): List<String> {
    val firstPart = line.substring(0,line.length/2)
    val secondPart = line.substring(line.length/2,line.length)
    return listOf(firstPart,secondPart)
}