package `2025`

import println
import readInput

fun main() {
    fun part1(input: List<String>): Long {
        val tasks = input.map { string -> string.split(Regex(" +")).filter { it.isNotBlank() } }
        val problems = tasks.dropLast(1).map { strings -> strings.map { it.toLong() } }
        val operations = tasks.takeLast(1).flatten()
        val size = problems[0].size
        var sum = 0L
        (0 until size).forEach { index ->
            val result = when (operations[index]) {
                "*" -> problems.map { it[index] }.reduce { a, b -> a * b }
                else -> problems.map { it[index] }.reduce { a, b -> a + b }
            }
            sum += result
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val matrix = input.dropLast(1)
            .rotate()
        val operations = input.last().split("").filter { it.isNotBlank() }.reversed()
        operations.println()
        matrix.println()

        val problems = buildList {
            val innerList = mutableListOf<Long>()

            matrix.forEach {
                if (it.isBlank()) {
                    if (innerList.isNotEmpty()) {
                        this.add(innerList.toList())
                        innerList.clear()
                    }
                } else {
                    innerList.add(it.toLong())
                }
            }
            if (innerList.isNotEmpty()) {
                this.add(innerList.toList())
            }
        }

        var sum = 0L
        problems.forEachIndexed { index, s ->
            val result = when (operations[index]) {
                "*" -> s.reduce { a, b -> a * b }
                else -> s.reduce { a, b -> a + b }
            }
            sum += result
        }
        return sum
    }

    val input = readInput("2025/Day06")
    part1(input).println()
    part2(input).println()
}

private fun List<String>.rotate(): List<String> {
    val rowSize = this.size
    val colSize = this[0].length * rowSize
    val result = MutableList(colSize) { MutableList(rowSize) { ' ' } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[colSize - j - 1][i] = this[i][j]
        }
    }

    return result.map { it.joinToString("").trim()}
}