package com.example.pgr202eksamen

import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer


class GameLogic(aiGame: Boolean) {
    private var player1: MutableList<Int> = emptyArray<Int>().toMutableList()
    private var player2: MutableList<Int> = emptyArray<Int>().toMutableList()
    var player1TurnBoolean: Boolean = true
    var aiPlayer2: Boolean = aiGame
    var turnCounter = 0
    lateinit var board: Array<IntArray>
    lateinit var moveHistory: MutableList<Int>




    fun setupBoard() {
        board = Array(4) { IntArray(4) }
        moveHistory = emptyArray<Int>().toMutableList()
        player1 = emptyArray<Int>().toMutableList()
        player2 = emptyArray<Int>().toMutableList()
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

    fun play(boxId: View) {
        val boxSelected = boxId as Button
        var boxId: Int = -1
        when (boxSelected.id) {
            R.id.b0 -> {
                boxId = 0
            }
            R.id.b1 -> {
                boxId = 1
            }
            R.id.b2 -> {
                boxId = 2
            }
            R.id.b3 -> {
                boxId = 3
            }
            R.id.b4 -> {
                boxId = 4
            }
            R.id.b5 -> {
                boxId = 5
            }
            R.id.b6 -> {
                boxId = 6
            }
            R.id.b7 -> {
                boxId = 7
            }
            R.id.b8 -> {
                boxId = 8
            }
            R.id.b9 -> {
                boxId = 9
            }
            R.id.b10 -> {
                boxId = 10
            }
            R.id.b11 -> {
                boxId = 11
            }
            R.id.b12 -> {
                boxId = 12
            }
            R.id.b13 -> {
                boxId = 13
            }
            R.id.b14 -> {
                boxId = 14
            }
            R.id.b15 -> {
                boxId = 15
            }
        }

        when (moveHistory.contains(boxId)) {
            true -> illegalMove()
            false -> legalMove(boxId)
        }
    }


    fun checkWinner(): Int {
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
                return 1
            }
            if (player2.containsAll(line.toMutableList())) {
                return 2

            }
        }
        if(turnCounter == 16){
            return 3
        }
        return -1
    }

    private fun legalMove(box: Int) {
        when (player1TurnBoolean) {
            true -> player1.add(box)
            false -> player2.add(box)
        }
        moveHistory.add(box)
        turnCounter++
        player1TurnBoolean = !player1TurnBoolean

    }

    private fun illegalMove(): String {
        return "You cant do that"
    }


}