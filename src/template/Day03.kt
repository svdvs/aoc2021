package template

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}
