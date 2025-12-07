package `2025`

import println
import readInput

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { it.maxNumber(base = 2) }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { it.maxNumber(base = 12) }

    }

    val input = readInput("2025/Day03")
    part1(input).println()
    part2(input).println()
}

private fun String.maxNumber(base: Int): Long {
    val listIndexed = this.mapIndexed { index, digit -> digit.digitToInt() to index }
    val digits = buildList<Pair<Int, Int>> {
        (1..base).forEach { i ->
            val startIndex = lastOrNull()?.let { it.second + 1 } ?: 0
            val currentMax = listIndexed.subList(startIndex, listIndexed.size - (base - i)).maxBy { it.first }
            add(currentMax)
        }
    }



    return digits.map { it.first }.joinToString("").toLong()
}