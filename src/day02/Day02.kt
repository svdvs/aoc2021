package day02

import readInput

enum class Command(val input: String) {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");

    companion object {
        private val map = Command.values().associateBy(Command::input)
        fun fromString(input: String): Command = map[input]!!
    }
}

data class NavCommand(val command: Command, val amount: Int) {}

class Position(var xPos: Int = 0, var yPos: Int = 0, var aim: Int = 0) {
    fun processNavigationCommands(commands: List<NavCommand>) : Int {
        commands.forEach {
            when(it.command) {
                Command.FORWARD -> {
                    xPos += it.amount
                    yPos += (aim * it.amount)
                }
                Command.DOWN -> aim += it.amount
                Command.UP -> aim -= it.amount
            }
        }

        return findCurrentPosition()
    }

    fun findCurrentPosition(): Int {
        return xPos * yPos
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val regex = """(\w+) (\d+)""".toRegex()

        val commands = input.map {
            val (command, amount) = regex.find(it)!!.destructured
            NavCommand(Command.fromString(command), amount.toInt())
        }

        return Position().processNavigationCommands(commands)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 900)

    val input = readInput("day02/Day02")
    println(part1(input))
}
