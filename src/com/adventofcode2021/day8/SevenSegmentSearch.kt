package com.adventofcode2021.day8

var counter = 0



fun task1() {
    var line = readLine()
    while(line!=null) {
        counter+= line.split("|")[1].split(" ").count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
        line = readLine()
    }
    print(counter)
}

fun main() {
    task1()
}

fun task2() {
    var line = readLine()
    while (line != null) {

    }
}

fun lineAnalyser(line: String) {

    var inputOutput = line.split("|")
    var input = inputOutput[0].split(" ").sortedBy { it.length }
    var output = inputOutput[1].split(" ")
}

fun charMapper(input : List<String>) {
    input.forEach {
        when (it.length) {
            2 -> null
            3 -> null //Digit 7
            4 -> null //Digit 4
            7 -> null //Digit 8
            else -> null
        }
    }
}

fun processInput(input:String) {
    Digit.digits().filter { it.segments.size==input.length }.forEach { fixSegment(it,input) }
}

fun fixSegment(digit: Digit,input: String) {
    input.toCharArray().forEach { c ->
           digit.segments.filter{s -> s.temp.isEmpty() && s.target=='\u0000'}.forEach { s -> s.temp.add(c) }
    }
    Segment.segments().filter { s -> s.temp.size == 1 }.forEach { s -> s.target = s.temp[0] }
    digit.segments.filter { s -> s.target == '\u0000' }
}

class Segment(var source:Char){
    var target: Char = '\u0000'
    val temp = mutableListOf<Char>()

    companion object {
        var a = Segment('a')
        var b = Segment('b')
        var c = Segment('c')
        var d = Segment('d')
        var e = Segment('e')
        var f = Segment('f')
        var g = Segment('g')

        fun segments() : List<Segment> {
            return listOf(a,b,c,d,e,f,g)
        }
    }
}

class Digit(val segments:List<Segment>) {

    companion object {
        var _1 = Digit(listOf(Segment.c,Segment.f))
        var _2 = Digit(listOf(Segment.a,Segment.c,Segment.d,Segment.e,Segment.g))
        var _3 = Digit(listOf(Segment.a,Segment.c,Segment.d,Segment.f,Segment.g))
        var _4 = Digit(listOf(Segment.b,Segment.c,Segment.d,Segment.f))
        var _5 = Digit(listOf(Segment.a,Segment.b,Segment.d,Segment.f,Segment.g))
        var _6 = Digit(listOf(Segment.a,Segment.b,Segment.d,Segment.e,Segment.f,Segment.g))
        var _7 = Digit(listOf(Segment.a,Segment.c,Segment.f))
        var _8 = Digit(listOf(Segment.a,Segment.b,Segment.c,Segment.d,Segment.e,Segment.f,Segment.g))
        var _9 = Digit(listOf(Segment.a,Segment.b,Segment.c,Segment.d,Segment.f,Segment.g))
        var _0 = Digit(listOf(Segment.a,Segment.b,Segment.c,Segment.e,Segment.f,Segment.g))

        //Need to find a better way of doing this
        fun digits() : List<Digit> {
            return listOf(_0,_1,_2,_3,_4,_5,_6,_7,_8)
        }
    }
}


















