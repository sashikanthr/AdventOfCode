package com.adventofcode2022.day10

var register = 1
var cycleCounter = 0
var signalStrength = 0
val cycleList = listOf(20,60,100,140,180,220)
val pixelCycle = listOf(40,80,120,160,200,240)
val pixels = StringBuilder()
var pixelRow = 0

fun main() {
    processInstructions()
}


fun processInstructions() {

    var instruction = readLine()
    while(instruction!=null) {
        processInstruction(instruction)
        instruction = readLine()
    }

    println("Signal Strength $signalStrength")
    println(pixels)

}

fun processInstruction(instruction:String) {
    var cyclesToComplete = cycleCounter
    var processing = false
    var newRegister = register
    while(cycleCounter<=cyclesToComplete) {
        drawPixel()
        if(cycleList.contains(cycleCounter)) {
            signalStrength+=(cycleCounter*register)
        }
        if(!processing) {
            cyclesToComplete = when {
                instruction.startsWith("noop") -> {
                    cycleCounter + 1
                }
                else -> {
                    newRegister = register + instruction.split(" ")[1].toInt()
                    cycleCounter + 2
                }
            }
            processing = true
        }
        cycleCounter++
        if(cyclesToComplete == cycleCounter) {
            register = newRegister
            processing = false
            cyclesToComplete = 0
        }
    }
}

//Part II
fun drawPixel() {
    if(pixelCycle.contains(cycleCounter)) {
        pixels.append("\n")
        pixelRow++
    }
    when (cycleCounter-(40*pixelRow)) {
        register, register-1, register+1 -> pixels.append("#")
        else -> pixels.append(".")
    }
}
