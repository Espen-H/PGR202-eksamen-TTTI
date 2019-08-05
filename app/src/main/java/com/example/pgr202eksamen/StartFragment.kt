package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var activePlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        activePlayer =  sharedPref.getString("active_player", null)!!

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_start, container, false)
        val btnStartAI: Button = view.findViewById(R.id.start_vsAI)
        val btnStart2Player: Button = view.findViewById(R.id.start_2player)
        val btnUsers: Button = view.findViewById(R.id.start_users)

        val welcomeMessage: TextView = view.findViewById(R.id.player_welcome)
        val welcomeText = "Hello $activePlayer"
        welcomeMessage.text = welcomeText

        btnStartAI.setOnClickListener {
            Toast.makeText((activity), "Starting game vs AI", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).replaceFragment("Ai")
        }

        btnStart2Player.setOnClickListener {
            Toast.makeText((activity), "This use this feature, you have to buy the multiplayer dlc for only $4.99", Toast.LENGTH_LONG).show()
            (activity as MainActivity).replaceFragment("2P")

        }

        btnUsers.setOnClickListener {
            Toast.makeText((activity), "Checking out the score history", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).replaceFragment("History")

        }

        return view
    }


}
