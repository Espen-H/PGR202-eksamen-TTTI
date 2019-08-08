package com.example.pgr202eksamen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class GameFragment(val mode: String, var player1: String) : Fragment() {

    private lateinit var viewOfLayout: View
    private lateinit var userModel: UserViewModel
    private lateinit var gameTime: Chronometer
    lateinit var player1Name: TextView
    lateinit var player1Score: TextView

    lateinit var player2Name: TextView
    lateinit var player2Score: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var player2 = ""
        when(mode) {
          "Ai" -> player2 = "TTTBot"
          "2P" ->
        }

        viewOfLayout = inflater.inflate(R.layout.fragment_game, container, false)
        gameTime = viewOfLayout.findViewById(R.id.chronometer)
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        player1Name.text = userModel.getUser(player1).userName
        player1Score.text = userModel.getUser(player1).score.toString()
        player2Name.text = userModel.getUser(player2).userName
        player2Score.text = userModel.getUser(player2).score.toString()
        return viewOfLayout

    }

}
