package com.example.pgr202eksamen

import android.util.Log
import android.view.View

class GameLogic(aiGame: Boolean) {
    private var player1: MutableList<Int> = emptyArray<Int>().toMutableList()
    private var player2: MutableList<Int> = emptyArray<Int>().toMutableList()
    var player1TurnBoolean: Boolean = true
    var aiPlayer2: Boolean = aiGame
    private var turnCounter = 0
    private lateinit var board: MutableMap<Int, Int>
    private lateinit var moveHistory: MutableList<Int>




    fun setupBoard() {
        board = mapOf(0 to 0, 1 to 1, 2 to 2, 3 to 3, 4 to 4, 5 to 5, 6 to 6, 7 to 7, 8 to 8,
                             9 to 9, 10 to 10, 12 to 12, 13 to 13, 14 to 14, 15 to 15).toMutableMap()
        player1TurnBoolean = true
        moveHistory = emptyArray<Int>().toMutableList()
        player1 = emptyArray<Int>().toMutableList()
        player2 = emptyArray<Int>().toMutableList()
        turnCounter = 0}


    fun play(button: View) {
        var box = -1
        when (button.id) {
            R.id.b0 -> {
                box = 0
            }
            R.id.b1 -> {
                box = 1
            }
            R.id.b2 -> {
                box = 2
            }
            R.id.b3 -> {
                box = 3
            }
            R.id.b4 -> {
                box = 4
            }
            R.id.b5 -> {
                box = 5
            }
            R.id.b6 -> {
                box = 6
            }
            R.id.b7 -> {
                box = 7
            }
            R.id.b8 -> {
                box = 8
            }
            R.id.b9 -> {
                box = 9
            }
            R.id.b10 -> {
                box = 10
            }
            R.id.b11 -> {
                box = 11
            }
            R.id.b12 -> {
                box = 12
            }
            R.id.b13 -> {
                box = 13
            }
            R.id.b14 -> {
                box = 14
            }
            R.id.b15 -> {
                box = 15
            }
        }

        when (player1TurnBoolean) {
            true -> player1.add(box)
            false -> player2.add(box)
        }

        moveHistory.add(box)
        Log.d("boxid", box.toString())
        board.remove(box)
        Log.d("after move", board.entries.toString()
        )
        turnCounter++
        player1TurnBoolean = !player1TurnBoolean
    }


    fun checkWinner(): Int {
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

}