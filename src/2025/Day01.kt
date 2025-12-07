package `2025`

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val steps = input.map { it[0] to it.substring(1).toInt() }
        var zeros = 0
        var current = 50

        steps.forEach { (direction, step) ->
            when (direction) {
                'L' -> current -= step
                'R' -> current += step
            }
            current = current.mod(100)
            if (current == 0) {
                zeros++
            }
        }

        return zeros
    }

    fun part2(input: List<String>): Int {
        val steps = input.map { it[0] to it.substring(1).toInt() }
        var zeros = 0
        var current = 50

        steps.forEach { (direction, step) ->
            val (newCurrent, foundZeros) = countZeros(current, step, if (direction == 'L') -1 else 1)
            zeros += foundZeros
            current = newCurrent
        }

        return zeros
    }

    val input = readInput("2025/Day01")
    part1(input).println()
    part2(input).println()
}

private fun countZeros(start: Int, steps: Int, direction: Int): Pair<Int, Int> {
    var zeros = 0
    val range = if (direction == 1) {
        (1..steps)
    } else {
        (1..steps).map { -it }
    }

    range.forEach {
        val current = (start + it).mod(100)
        if (current == 0) {
            println("The dial is rotated $direction $steps to point at 0.")
            zeros++
        }
    }

    return (start + direction * steps).mod(100) to zeros
}