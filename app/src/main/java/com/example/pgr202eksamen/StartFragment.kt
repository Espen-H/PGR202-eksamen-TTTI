package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.alert_dialog_edittext.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StartFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var activePlayer: String
    private lateinit var userModel: UserViewModel
    private var player2Chosen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        activePlayer = sharedPref.getString("active_player", null)!!

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_start, container, false)
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val btnStartAI: Button = view.findViewById(R.id.start_vsAI)
        val btnStart2Player: Button = view.findViewById(R.id.start_2player)
        val btnUsers: Button = view.findViewById(R.id.start_users)

        val welcomeMessage: TextView = view.findViewById(R.id.player_welcome)
        val welcomeText = "Hello $activePlayer"
        welcomeMessage.text = welcomeText



        btnStartAI.setOnClickListener {
            (activity as MainActivity).replaceFragment("Ai")
        }

        fun createOrAddPlayer2(username: String) {
            GlobalScope.launch {
                try {

                    if (userModel.getUser(username).userName == username) {
                        (activity as MainActivity).player2 = username
                        player2Chosen = true
                        (activity as MainActivity).replaceFragment("2P")
                    }
                } catch (e: NullPointerException) {
                    val newUser = User(username, 0)
                    userModel.insert(newUser)
                    (activity as MainActivity).player2 = username
                    player2Chosen = true
                    (activity as MainActivity).replaceFragment("2P")
                }
            }
        }


        fun choosePlayer2Alert(view: View) {
            val builder = AlertDialog.Builder(this.context!!)
            val inflater = layoutInflater
            builder.setTitle("Add player 2")
            val dialogLayout = inflater.inflate(R.layout.alert_dialog_edittext, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            builder.setView(dialogLayout)
            builder.setPositiveButton("OK") { dialogInterface, i -> createOrAddPlayer2(editText.text.toString()) }
            builder.show()
        }


        btnStart2Player.setOnClickListener {

            when (player2Chosen) {
                true -> (activity as MainActivity).replaceFragment("2P")
                false -> {
                    choosePlayer2Alert(this.view!!)

                }
            }
        }

        btnUsers.setOnClickListener {
            (activity as MainActivity).replaceFragment("History")

        }

        return view
    }


}
