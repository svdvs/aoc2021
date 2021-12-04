package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val heights = input.map { it.toInt() }.toMutableList()
        var count = 0
        var lastHeight = heights.removeAt(0)

        heights.forEach {
            if(it > lastHeight) count += 1
            lastHeight = it
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val heights = input.map { it.toInt() }.toMutableList()
        var count = 0

        heights.forEachIndexed { index, current ->
            val next = heights.getOrNull(index + 3)
            next?.let {
                if (next > current) count += 1
            }
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("day01/Day01")
    println(part1(input))
    println(part2(input))
}
