package com.example.pgr202eksamen


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class SignUpFragment : Fragment() {

      private lateinit var userModel: UserViewModel

    /*
        Simple EditText that will be used as the username. When button is clicked
        Username is saved in sharedPref as active player using UpdateSharedPref
        A User is also created using the InsertUserInfo and added to the RoomUserDb
        Finally it sends the user back to the Start fragment
     */


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val viewOfLayout = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val signUpButton: Button = viewOfLayout.findViewById(R.id.signup_button)
        Log.d("signUpFragment", "View created")
        signUpButton.setOnClickListener {
            val username: EditText = viewOfLayout.findViewById(R.id.player_name)
            val usernameText:String = username.text.toString()
            val newUser = User(usernameText, 0)
            (activity as MainActivity).updateSharedPref(usernameText)
            userModel.insert(newUser)
            userModel.insert(User("TTTBot", 0))
            (activity as MainActivity).replaceFragment("Start")
        }
        return viewOfLayout

    }

}
