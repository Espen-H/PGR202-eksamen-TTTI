package com.example.pgr202eksamen


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SignUpFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewOfLayout = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val signUpButton: Button = viewOfLayout.findViewById(R.id.signup_button)
        Log.d("signUpFragment", "View created")
        signUpButton.setOnClickListener {
            Log.d("signUpButton", "button clicked")
            val username: EditText = viewOfLayout.findViewById(R.id.player_name)
            val usernameText:String = username.text.toString()

            (activity as MainActivity).updateSharedPref(usernameText)
            (activity as MainActivity).replaceFragment("Start")
            (activity as MainActivity)
        }
        return viewOfLayout

    }

}
