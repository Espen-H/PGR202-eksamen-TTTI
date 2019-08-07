package com.example.pgr202eksamen

class GameLogic(aiGame: Boolean) {

    private val player1: MutableList<Int> = emptyArray<Int>().toMutableList()
    private val player2: MutableList<Int> = emptyArray<Int>().toMutableList()
    private var player1TurnBoolean: Boolean = true
    private var aiPlayer2: Boolean = aiGame
    private var turnCounter = 0

    private lateinit var board: Array<IntArray>
    private lateinit var moveHistory: MutableList<Int>

    private fun setupBoard() {
        board = Array(4) { IntArray(4) }
        moveHistory = emptyArray<Int>().toMutableList()
        turnCounter = 0

        //Random player starts
        when ((0..1).random()) {
            0 -> player1TurnBoolean = true
            1 -> player1TurnBoolean = false
        }
        var numId = 0
        for (i in 0 until board.size) {
            val colArray = IntArray(4)

            for (j in 0 until colArray.size) {
                colArray[j] = numId++
            }
            board[i] = colArray
        }
    }

    /*
            Restricting players from choosing the same box will be done with the onClick event on the boxes
     */

    private fun play(boxId: Int) {
        when (moveHistory.contains(boxId)) {
            true -> return illegalMove()
            false ->
                when (player1TurnBoolean) {
                    true -> player1.add(boxId)
                    false -> player2.add(boxId)
                }
        }
        moveHistory.add(boxId)
    }

    private fun illegalMove() {
        /*
         #TODO
         Make Toast telling the player that the move was against the rules
       */

    }

    private fun checkWinner() {
        var winner = -1
        val winningLines: Collection<IntArray> =
            mutableListOf(
                // horizontal
                intArrayOf(0, 1, 2, 3),
                intArrayOf(4, 5, 6, 7),
                intArrayOf(8, 9, 10, 11),
                intArrayOf(12, 13, 14, 15),
                //vertical
                intArrayOf(0, 4, 8, 12),
                intArrayOf(1, 5, 9, 13),
                intArrayOf(2, 6, 10, 14),
                intArrayOf(3, 7, 11, 16),
                // diagonal
                intArrayOf(0, 5, 10, 15),
                intArrayOf(3, 6, 9, 12)

            )
        for (line in winningLines) {
            if (player1.containsAll(line.toMutableList())) {
                winner = 1
                return // Tell the UI that game is over
            }
            if (player2.containsAll(line.toMutableList())) {
                winner = 2
                return // Tell the UI that game is over

            }
        }
    }
}