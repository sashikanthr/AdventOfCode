package com.adventofcode2022.day1

var maxSum1 = 0
var maxSum2 = 0
var maxSum3 = 0


fun main() {
    var line = readLine()
    var sum = 0
    while (line != null) {
        if (line.isEmpty()) {
            compare(sum)
            sum = 0
        } else {
            sum += line.toInt()
        }
        line = readLine()
    }
    compare(sum)
    println("Maxsum1:$maxSum1")
    println("Maxsum2:$maxSum2")
    println("Maxsum3:$maxSum3")
    println("Total sum:"+(maxSum1+maxSum2+maxSum3))

}

//We can also use list and sort for task 2
fun compare(sum: Int) {
    if (sum > maxSum1) {
        maxSum3 = maxSum2
        maxSum2 = maxSum1
        maxSum1 = sum
    } else if (sum > maxSum2) {
        maxSum3 = maxSum2
        maxSum2 = sum
    } else if (sum > maxSum3) {
        maxSum3 = sum
    }
}