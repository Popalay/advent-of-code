package `2025`

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val emptyLineIndex = input.indexOfFirst { it.isBlank() }
        val freshIds = input.subList(0, emptyLineIndex)
            .map { it.split("-") }
            .map { LongRange(it[0].toLong(), it[1].toLong()) }
        val ids = input.subList(emptyLineIndex + 1, input.size)
            .map { it.toLong() }

        return ids.count { id -> freshIds.any { id in it } }
    }

    fun part2(input: List<String>): Long {
        val emptyLineIndex = input.indexOfFirst { it.isBlank() }
        val ranges = input.subList(0, emptyLineIndex)
            .map { it.split("-") }
            .map { LongRange(it[0].toLong(), it[1].toLong()) }
        val mergedRanges = mergeRanges(ranges)
        return mergedRanges.sumOf { it.last - it.first + 1 }
    }

    val input = readInput("2025/Day05")
    part1(input).println()
    part2(input).println()
}


private fun LongRange.isMergeable(other: LongRange): Boolean {
    val isSubRange = this.first >= other.first && this.last <= other.last ||
            other.first >= this.first && other.last <= this.last
    if (isSubRange) return true

    return this.last + 1 >= other.first && this.first <= other.last + 1
}

private fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
    val sorted = ranges.sortedBy { it.first }
    val result = mutableListOf<LongRange>()
    var currentRange: LongRange? = null

    for (range in sorted) {
        if (currentRange == null) {
            currentRange = range
        } else if (currentRange.isMergeable(range)) {
            currentRange = LongRange(
                minOf(currentRange.first, range.first),
                maxOf(currentRange.last, range.last)
            )
        } else {
            result.add(currentRange)
            currentRange = range
        }
    }

    currentRange?.let { result.add(it) }
    return result
}