package `2025`

import Point
import get
import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray().toList() }
        var result = 0

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if (cell != '.' && grid.rollsCount(Point(x, y)) < 4) {
                    result++
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toCharArray().toList().toMutableList() }
        var result = 0

        do {
            var removed = 0
            grid.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    if (cell != '.' && grid.rollsCount(Point(x, y)) < 4) {
                        grid[y][x] = '.'
                        removed++
                    }
                }
            }
            result += removed
        } while (removed > 0)

        return result
    }

    val input = readInput("2025/Day04")
    part1(input).println()
    part2(input).println()
}

private fun List<List<Char>>.rollsCount(startPosition: Point): Int = listOf(
    Point(0, -1),
    Point(0, 1),
    Point(-1, 0),
    Point(1, 0),
    Point(1, 1),
    Point(-1, -1),
    Point(-1, 1),
    Point(1, -1),
)
    .map { this[it + startPosition] }.count { it == '@' }