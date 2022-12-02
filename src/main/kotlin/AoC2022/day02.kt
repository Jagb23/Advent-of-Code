package AoC2022

import java.io.File
import java.nio.file.Paths

enum class Plays(val value: Int) {
    A(1),
    B(2),
    C(3),
    X(1),
    Y(2),
    Z(3);
}

enum class RPS (val value: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun fromInt(value: Int) = RPS.values().first { it.value == value }
    }
}

var WIN = 6
var DRAW = 3

fun partOne(fileName: String) {
    var myScore = 0

    File(fileName).forEachLine {
        val words = it.split("\\s".toRegex()).toTypedArray()
        val opponentsPlay = RPS.fromInt(Plays.valueOf(words[0]).value)
        val myPlay = RPS.fromInt(Plays.valueOf(words[1]).value)


       myScore += getScore(myPlay, opponentsPlay)
    }

    println(myScore)
}

fun partTwo(fileName: String) {
    var myScore = 0
    /*
    * X = LOSE
    * Y = DRAW
    * Z = WIN
    * */
    File(fileName).forEachLine {
        val words = it.split("\\s".toRegex())
        val opponentsPlay = RPS.fromInt(Plays.valueOf(words[0]).value)

        val myPlay: RPS
        when (words[1]) {
            "Z" -> myPlay = win(opponentsPlay)
            "X" -> myPlay = loose(opponentsPlay)
            else -> myPlay = opponentsPlay
        }

        myScore += getScore(myPlay, opponentsPlay)
    }

    println(myScore)
}

fun win(opponentsPlay: RPS): RPS {
    return when (opponentsPlay) {
        RPS.ROCK -> RPS.PAPER
        RPS.PAPER -> RPS.SCISSORS
        else -> RPS.ROCK
    }
}

fun loose(opponentsPlay: RPS): RPS {
    return when (opponentsPlay) {
        RPS.ROCK -> RPS.SCISSORS
        RPS.PAPER -> RPS.ROCK
        else -> RPS.PAPER
    }
}

fun getScore(myPlay: RPS, opponentsPlay: RPS): Int {
    var myScore = 0
    if (myPlay == opponentsPlay) {
        // draw
        myScore += myPlay.value + DRAW
    } else if (myPlay == RPS.ROCK) {
        if (opponentsPlay == RPS.SCISSORS) {
            myScore += myPlay.value + WIN
        } else {
            myScore += myPlay.value // opponent played paper and won
        }
    } else if (myPlay == RPS.PAPER) {
        if (opponentsPlay == RPS.ROCK) {
            myScore += myPlay.value + WIN
        } else {
            myScore += myPlay.value // opponent played scissors and won
        }
    } else if (myPlay == RPS.SCISSORS) {
        if (opponentsPlay == RPS.PAPER) {
            myScore += myPlay.value + WIN
        } else {
            myScore += myPlay.value // opponent played rock and won
        }
    }

    return myScore
}

fun main(args: Array<String>) {
    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()

    var inputFileName = Paths.get(projectDirAbsolutePath, "/src/main/resources/day02/input.txt").toString()
    var testInputFileName = Paths.get(projectDirAbsolutePath, "/src/main/resources/day02/test-input.txt").toString()

    partOne(inputFileName) // 10404
    partTwo(inputFileName) // 10334
}