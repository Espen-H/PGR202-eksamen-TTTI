package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var activeUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        if (!sharedPref.contains("active_player")) {
            replaceFragment("Signup")
        } else {
            activeUser = sharedPref.getString("active_player", null)!!
            replaceFragment("Start")
        }
    }

    // When the back button is pressed
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    //Updates active player
    fun updateSharedPref(value: String) {
        with(sharedPref.edit()) {
            putString("active_player", value)
            apply()
        }
    }



    fun replaceFragment(string: String) {
        val fragmentManager = supportFragmentManager
        when (string) {
            "Ai" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("Ai", activeUser, "" )).addToBackStack("Ai").commit()
            "2P" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("2P", activeUser, "")).addToBackStack("2P").commit()
            "Signup" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, SignUpFragment()).commit()
            "Start" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, StartFragment()).addToBackStack("Start").commit()
            "History" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, HistoryFragment()).addToBackStack("History").commit()
        }
    }
}
