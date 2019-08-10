package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.alert_dialog_edittext.*


class StartFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var activePlayer: String
    private lateinit var userModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        activePlayer = sharedPref.getString("active_player", "")!!

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

        fun newUserDialog() {
            val builder = context?.let { AlertDialog.Builder(it) }
            val inflater = requireActivity().layoutInflater
            builder!!.setView(inflater.inflate(R.layout.alert_dialog_edittext, null))
                .setPositiveButton("Finish") { dialog, id ->
                    val username = editText.text.toString()
                    val newUser = User(username, 0)
                    userModel.insert(newUser)
                }
            builder.create()
        }


        btnStart2Player.setOnClickListener {
           // (activity as MainActivity).replaceFragment("2P")
        }

        btnUsers.setOnClickListener {
            (activity as MainActivity).replaceFragment("History")

        }

        return view
    }


}
