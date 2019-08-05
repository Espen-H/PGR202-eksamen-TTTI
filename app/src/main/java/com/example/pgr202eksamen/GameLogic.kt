package com.example.pgr202eksamen

class GameLogic() {

    private val player1: MutableList<Int> = emptyArray<Int>().toMutableList()
    private val player2: MutableList<Int> = emptyArray<Int>().toMutableList()

    fun checkWinner() {
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
            }
            if (player2.containsAll(line.toMutableList())) {
                winner = 2

            }
        }
    }
}