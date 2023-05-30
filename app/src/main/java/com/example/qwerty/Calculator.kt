package com.example.qwerty

import com.example.qwerty.model.Point
import java.io.File
import kotlin.math.sqrt

class Calculator {

    /*
    Read file from storage and add to ArrayList as Point
     */
    fun readFile(filePath: String): ArrayList<Point> {
        val file = File(getClassFilePath(this) + filePath)
        val lines = ArrayList<String>()
        file.forEachLine { line ->
            lines.add(line)
        }

        val instance = ArrayList<Point>()
        for (i in lines.indices) {
            val params = lines[i].split(",")

            val id = params[0].toInt()
            val x = params[1].toDouble()
            val y = params[2].toDouble()
            instance.add(Point(id, x, y))
        }

        return instance
    }


    // Calculation to get distance between two points
    private fun getDistance(point1: Point, point2: Point): Double {
        val dx = point2.x - point1.x
        val dy = point2.y - point1.y
        return sqrt(dx * dx + dy * dy)
    }


    fun greedy(cities: ArrayList<Point>, index: Int): Double {
        val visitedCities = BooleanArray(cities.size) { false } // easy way to create and set default value for boolean list
        visitedCities[index] = true // used to be static 0 but be changed to dynamic to change results
        val tour = arrayListOf<Int>()
        tour.add(index + 1)

        var currentCityIndex = 0
        var totalDistance = 0.0


        repeat(cities.size - 1) {
            var closestCityIndex = -1
            var minDistance = Double.MAX_VALUE // to get largest number

            // Looking for smallest distance from city 1
            for (cityIndex in cities.indices) {
                if (!visitedCities[cityIndex] && cityIndex != currentCityIndex) { // not visited and not same as current
                    val distance = getDistance(cities[currentCityIndex], cities[cityIndex])
                    if (distance < minDistance) {
                        minDistance = distance
                        closestCityIndex = cityIndex
                    }
                }
            }

            // Getting closest remaining city
            visitedCities[closestCityIndex] = true // It's visited now
            tour.add(closestCityIndex + 1) // Added to tour
            totalDistance += minDistance
            currentCityIndex = closestCityIndex // new currentCityIndex update
        }

        // Distance between last and first city
        totalDistance += getDistance(cities[currentCityIndex], cities[0])

        println("Tour: $tour")
        println("Total distance: $totalDistance")
        return totalDistance
    }


    ///To find current package path
    private fun getClassFilePath(calculatorClass: Calculator): String? {
        val classFilePath =
            calculatorClass.javaClass.protectionDomain?.codeSource?.location?.toURI()?.path
        return classFilePath?.replace(
            "/build/tmp/kotlin-classes/debug/",
            "/src/main/java/com/example/qwerty"
        )
    }

}