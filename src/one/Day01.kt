package one

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
    val testInput = readInput("one/Day01_test")
    check(part2(testInput) == 6)

    val input = readInput("one/Day01")
    println(part2(input))
//    println(part2(input))
}
