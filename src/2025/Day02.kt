package `2025`

import println
import readInputRaw

fun main() {
    fun part1(input: List<String>): Long {
        return input.map { it.substringBefore("-").toLong()..it.substringAfter("-").toLong() }
            .flatMap { it.asSequence() }
            .filterNot { it.isValid1() }
            .sum()
    }

    fun part2(input: List<String>): Long {
        return input.map { it.substringBefore("-").toLong()..it.substringAfter("-").toLong() }
            .flatMap { it.asSequence() }
            .filterNot { it.isValid2() }
            .sum()
    }

    val input = readInputRaw("2025/Day02").split(',')
    part1(input).println()
    part2(input).println()
}

private fun Long.isValid1(): Boolean {
    val str = this.toString()
    return str.take(str.length / 2) != str.substring(str.length / 2)
}

private fun Long.isValid2(): Boolean {
    val str = this.toString()
    return ((str.length / 2) downTo 1).any { n ->
        str.chunked(n).distinct().count() == 1
    }.not()
}