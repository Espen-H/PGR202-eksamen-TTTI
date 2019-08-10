package com.example.pgr202eksamen


import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class GameFragment(private val mode: String, private var player1: String, private var player2: String) : Fragment() {

    private lateinit var viewOfLayout: View
    private lateinit var gameLogic: GameLogic
    private lateinit var userModel: UserViewModel
    private lateinit var gameTime: Chronometer
    private lateinit var user1: User
    private lateinit var user2: User
    private lateinit var player1Name: TextView
    private lateinit var player1Score: TextView
    private lateinit var player2Name: TextView
    private lateinit var player2Score: TextView
    private lateinit var turnFormat: String
    private lateinit var p1turn: String
    private lateinit var p2turn: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewOfLayout = inflater.inflate(R.layout.fragment_game, container, false)
         turnFormat =  resources.getString(R.string.player_turn)
         p1turn= String.format(turnFormat, player1)
         p2turn= String.format(turnFormat, player2)

        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        gameTime = viewOfLayout.findViewById(R.id.chronometer)
        when (mode) {
            "Ai" -> {
                gameLogic = GameLogic(true)
            }
            "2P" -> {
                gameLogic = GameLogic(false)
            }
        }

        player1Name = viewOfLayout.findViewById(R.id.active_player)
        player1Score = viewOfLayout.findViewById(R.id.player_score)
        player2Name = viewOfLayout.findViewById(R.id.active_player2)
        player2Score = viewOfLayout.findViewById(R.id.player_score2)


        val btn0: Button = viewOfLayout.findViewById(R.id.b0)
        val btn1: Button = viewOfLayout.findViewById(R.id.b1)
        val btn2: Button = viewOfLayout.findViewById(R.id.b2)
        val btn3: Button = viewOfLayout.findViewById(R.id.b3)
        val btn4: Button = viewOfLayout.findViewById(R.id.b4)
        val btn5: Button = viewOfLayout.findViewById(R.id.b5)
        val btn6: Button = viewOfLayout.findViewById(R.id.b6)
        val btn7: Button = viewOfLayout.findViewById(R.id.b7)
        val btn8: Button = viewOfLayout.findViewById(R.id.b8)
        val btn9: Button = viewOfLayout.findViewById(R.id.b9)
        val btn10: Button = viewOfLayout.findViewById(R.id.b10)
        val btn11: Button = viewOfLayout.findViewById(R.id.b11)
        val btn12: Button = viewOfLayout.findViewById(R.id.b12)
        val btn13: Button = viewOfLayout.findViewById(R.id.b13)
        val btn14: Button = viewOfLayout.findViewById(R.id.b14)
        val btn15: Button = viewOfLayout.findViewById(R.id.b15)
        val resetBtn: Button = viewOfLayout.findViewById(R.id.reset)

        resetBtn.setOnClickListener {
            gameLogic.setupBoard()
        }

        val buttons: Array<Button> = arrayOf(
            btn0,
            btn1,
            btn2,
            btn3,
            btn4,
            btn5,
            btn6,
            btn7,
            btn8,
            btn9,
            btn10,
            btn11,
            btn12,
            btn13,
            btn14,
            btn15
        )

        fun disableButtons() {
            for (button in buttons) {
                button.isEnabled = false
            }
        }

        suspend fun updateUser(user: User) {
            userModel.updateUser(user)
        }

        for (button in buttons) {
            button.isEnabled = true
            button.setOnClickListener {
                gameLogic.play(it)


                when (gameLogic.player1TurnBoolean) {
                    true -> {
                        button.text = "X"
                        current_turn.text = p1turn
                    }
                    false -> {
                        button.text = "O"
                        current_turn.text = p2turn
                    }
                }
                it.isEnabled = false
                if (gameLogic.turnCounter > 6) {
                    when (gameLogic.checkWinner()) {
                        1 -> {
                            disableButtons()
                            gameTime.stop()
                            Toast.makeText(this.context, "$player1 won the game!", Toast.LENGTH_LONG).show()
                            user1.score++
                            GlobalScope.launch { updateUser(user1) }


                        }
                        2 -> {
                            disableButtons()
                            gameTime.stop()
                            Toast.makeText(this.context, "$player2 won the game!", Toast.LENGTH_LONG).show()
                            user2.score++
                            GlobalScope.launch { updateUser(user2) }


                        }
                        3 -> {
                            disableButtons()
                            gameTime.stop()
                            Toast.makeText(this.context, "Its a draw", Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }

        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fun initializeUsers() {
            player1Name.text = user1.userName
            player1Score.text = user1.score.toString()
            player2Name.text = user2.userName
            player2Score.text = user2.score.toString()
        }

        suspend fun getUserData() {
            user1 = userModel.getUser(player1)
            user2 = userModel.getUser(player2)

            activity?.runOnUiThread { initializeUsers() }
        }

        when (gameLogic.player1TurnBoolean) {
            true -> {
                current_turn.text = p1turn
            }
            false -> {
                current_turn.text = p2turn
            }
        }

        GlobalScope.launch { getUserData() }
        gameLogic.setupBoard()
        gameTime.base = SystemClock.elapsedRealtime()
        gameTime.start()
    }
}



