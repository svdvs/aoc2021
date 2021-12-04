package day03

import readInput

enum class FLAGS {
    MIN,
    MAX
}

fun main() {
    fun stringToIntList(input: String): List<Int> {
        return input.map { it.toString().toInt() }
    }
    fun createColumnArray(input: List<String>):Map<Int,MutableList<Int>> {
        return input.fold(mutableMapOf()) { acc, binaryString ->
            stringToIntList(binaryString).forEachIndexed { index, value ->
                val list = acc.getOrPut(index) { mutableListOf() }
                list.add(value)
            }

            acc
        }
    }

    fun getMostCommonInList(list: List<Int>) : Int? {
        val countList = list.groupBy { it }.mapValues { it.value.size }
        return if (countList[0] == countList[1]) {
            1
        } else countList.maxByOrNull { it.value }?.key
    }

    fun getLeastCommonInList(list: List<Int>) : Int? {
        val countList = list.groupBy { it }.mapValues { it.value.size }
        return if (countList[0] == countList[1]) {
            0
        } else countList.minByOrNull { it.value }?.key
    }

    fun part1(input: List<String>): Int {
        val binaryMap = createColumnArray(input)
        var gamma = ""
        var epsilon = ""

        for ((_, value) in binaryMap) {
            gamma = gamma.plus(getMostCommonInList(value))
            epsilon = epsilon.plus(getLeastCommonInList(value))
        }

        return (Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2))
    }


    fun part2(input: List<String>): Int {
        val oxygenArr = input.toMutableList()
        val co2Arr = input.toMutableList()
        var oxygenLoopCounter = 0
        var co2LoopCounter = 0

        while(oxygenArr.size > 1) {
            val oxygenMap = createColumnArray(oxygenArr)
            val oxygemDelim = getMostCommonInList(oxygenMap[oxygenLoopCounter]!!)!!
            oxygenArr.retainAll { it[oxygenLoopCounter].toString() == oxygemDelim.toString() }
            oxygenLoopCounter++
        }

        while(co2Arr.size > 1) {
            val co2Map = createColumnArray(co2Arr)
            val co2Delim = getLeastCommonInList(co2Map[co2LoopCounter]!!)!!
            co2Arr.retainAll { it[co2LoopCounter].toString() == co2Delim.toString() }
            co2LoopCounter++
        }

        return (Integer.parseInt(oxygenArr[0], 2) * Integer.parseInt(co2Arr[0], 2))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
//    check(part1(testInput) == 198)
    val a = part2(testInput)
    check(part2(testInput) == 230)
//
    val input = readInput("day03/Day03")
    println(part2(input))
//    println(part2(input))
}
