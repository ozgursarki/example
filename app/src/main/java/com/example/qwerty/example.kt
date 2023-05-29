package com.example.qwerty

import kotlin.system.measureTimeMillis

fun main() {

    val calculator = Calculator()
    val burma14Instance = calculator.readFile("/burma14.txt")
    val berlin52Instance = calculator.readFile("/berlin52.txt")


    val burma14Results = arrayListOf<Long>()
    val berlin52Results = arrayListOf<Long>()

    var bestRunTimeBurma14 = Long.MAX_VALUE
    var bestRunTimeBerlin52 = Long.MAX_VALUE
    var bestDistanceBurma14 = Double.MAX_VALUE
    var bestDistanceBerlin52 = Double.MAX_VALUE

    repeat(10) {
        val burma14Time = measureTimeMillis {
            val distance = calculator.greedy(burma14Instance, it)
            burma14Results.add(distance.toLong())

            if (distance < bestDistanceBurma14) {
                bestDistanceBurma14 = distance
            }
        }
        if (burma14Time < bestRunTimeBurma14) {
            bestRunTimeBurma14 = burma14Time
        }

        val berlin52Time = measureTimeMillis {
            val distance = calculator.greedy(berlin52Instance, it)
            berlin52Results.add(distance.toLong())

            if (distance < bestDistanceBerlin52) {
                bestDistanceBerlin52 = distance
            }
        }
        if (berlin52Time < bestRunTimeBerlin52) {
            bestRunTimeBerlin52 = berlin52Time
        }
    }

    val averageRunTimeBurma14 = calculateAverage(burma14Results)
    val averageDistanceBurma14 = burma14Results.average()
    val averageRunTimeBerlin52 = calculateAverage(berlin52Results)
    val averageDistanceBerlin52 = berlin52Results.average()

    println("Best Run Time (ms) for Burma14: $bestRunTimeBurma14")
    println("Best Distance for Burma14: $bestDistanceBurma14")
    println("Average Run Time (ms) for Burma14: $averageRunTimeBurma14")
    println("Average Distance for Burma14: $averageDistanceBurma14")

    println("Best Run Time (ms) for Berlin52: $bestRunTimeBerlin52")
    println("Best Distance for Berlin52: $bestDistanceBerlin52")
    println("Average Run Time (ms) for Berlin52: $averageRunTimeBerlin52")
    println("Average Distance for Berlin52: $averageDistanceBerlin52")


}

fun calculateAverage(numbers: ArrayList<Long>): Long {
    if (numbers.isEmpty()) {
        return 0L
    }

    val sum = numbers.sum()
    return sum / numbers.size
}