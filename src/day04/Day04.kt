package day04

import readInput

data class BoardValue(val number: String, var isMarked: Boolean = false)

data class Board(val value: Map<Pair<Int, Int>, BoardValue>, var hasWon: Boolean) {
    companion object {
        fun build(inputList: List<String>): Board {
            val board = mutableMapOf<Pair<Int, Int>, BoardValue>()
            inputList.forEachIndexed() { rowIndex, row ->
                row.split(" ").filter{ it.isNotEmpty() }.forEachIndexed { colIndex, num ->
                    board.put(Pair(rowIndex, colIndex), BoardValue(num))
                }
            }
            return Board(board, false)
        }
    }
}

data class Game(val numbers: List<String>, val boards: List<Board>)

fun main() {
    fun getGame(input: MutableList<String>) : Game {
        val numbers = input.removeAt(0)
        val boards = mutableListOf<List<String>>()
        val currentBoard = mutableListOf<String>()

        input.forEach {
            if (it.isEmpty()) {
                if (currentBoard.size > 0) {
                    val newBoard = currentBoard.toMutableList()
                    boards.add(boards.size, newBoard)
                    currentBoard.clear()
                }
            } else {
                currentBoard.add(currentBoard.size, it)
            }
        }

        if (currentBoard.isNotEmpty()) { boards.add(boards.size, currentBoard) }

        return Game(numbers.split(","), boards.map { Board.build(it) })
    }

    fun checkRow(thisRow: Map<Pair<Int, Int>, BoardValue>): Boolean {
        return thisRow.values.none { !it.isMarked }

    }

    fun checkColumn(thisCol: Map<Pair<Int, Int>, BoardValue>): Boolean {
        return thisCol.values.none { !it.isMarked }
    }

    fun checkGame(game: Game): Board? {
        var winningBoard: Board? = null
        for (board in game.boards) {
            if (!board.hasWon) {
                for (position in board.value) {
                    val thisRow = board.value.filter { it.key.first == position.key.first }
                    val thisCol = board.value.filter { it.key.second == position.key.second }
                    if (checkRow(thisRow)) {
                        winningBoard = board
                        break
                    } else if (checkColumn(thisCol)) {
                        winningBoard = board
                        break
                    }
                }
            }
        }


        return winningBoard
    }

    fun part1(input: List<String>): Int {
        val game = getGame(input.toMutableList())
        var answer = 0
        val winningBoards = mutableListOf<Board>()
        var lastWinningNumber = 0

        for(number in game.numbers) {
            if (answer != 0) break
            game.boards.forEach { board ->
                if(!board.hasWon) {
                    board.value.values.forEach { boardValue ->
                        if (boardValue.number == number) {
                            boardValue.isMarked = true
                            checkGame(game)?.let {
                                if (!it.hasWon) {
                                    winningBoards.add(winningBoards.size, it)
                                    it.hasWon = true
                                    lastWinningNumber = number.toInt()
                                da}
                            }
                        }
                    }
                }
            }

        }

        val boardSum = winningBoards.last().value.values.filter{ !it.isMarked }.map { it.number.toInt() }.sum()
        answer = boardSum * lastWinningNumber

        return answer
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 1924)
//    check(part2(testInput) == 5)
//
    val input = readInput("day04/Day04")
    println(part1(input))
//    println(part2(input))
}
