import java.io.File
import java.nio.file.Paths

// https://adventofcode.com/2022/day/1

fun findTopElfPartOne(fileName: String) {
    var elfWithMostCaloriesIndex = -1
    var mostCalories = -1

    var curElf = 1
    var curCaloriesAmount = 0
    File(fileName).forEachLine {
        if (it.isBlank()) {
            if (curCaloriesAmount > mostCalories) {
                elfWithMostCaloriesIndex = curElf
                mostCalories = curCaloriesAmount
            }
            curCaloriesAmount = 0
            ++curElf
        } else {
            curCaloriesAmount += it.toInt()
        }

    }

    println("Part 1: $mostCalories calories")
}

fun findTopThreeElvesPartTwo(fileName: String) {
    val topThree = intArrayOf(0, 0, 0)

    var curCalories = 0
    File(fileName).forEachLine {
        if (it.isBlank() || it.isEmpty()) {
            if (curCalories > topThree[0]) {
                topThree[0] = curCalories
                topThree.sort() // sort so the smallest amount is now at index 0
            }
            curCalories = 0
        } else {
            curCalories += it.toInt()
        }

    }

    if (curCalories != 0 && curCalories > topThree[0]) {
        topThree[0] = curCalories
        topThree.sort()
    }

    println("Part 2: " + topThree.sum() + " calories")
}

fun main(args: Array<String>) {
    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()

    var inputFileName = Paths.get(projectDirAbsolutePath, "/src/main/resources/day01/d1-input.txt").toString()
    var testInputFileName = Paths.get(projectDirAbsolutePath, "/src/main/resources/day01/d1-test-input.txt").toString()

    findTopElfPartOne(inputFileName)
    findTopThreeElvesPartTwo(inputFileName)
}