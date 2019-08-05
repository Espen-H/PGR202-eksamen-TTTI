package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "com.example.pgr202eksamen"
        ).build()
        if (!sharedPref.contains("active_player")) {
            replaceFragment("Signup")
        } else {
            replaceFragment("Start")
        }
        Thread {
        if(database.userDao().findByName("TTTBot").userName != "TTTBot") run {
            val TTTBot: User = User(0, "TTTBot", 0)
            database.userDao().insert(TTTBot)
        }}
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

    // Inserts user into the database
    fun insertUserInfo(user: String) {
        Thread {
            val newUser: User = User(0, user, 0)
            database.userDao().insert(newUser)
        }
    }

    // updates the user in the database
    fun updateUserScore(user: User) {
        Thread {
            database.userDao().update(user)
        }
    }


    fun replaceFragment(string: String) {
        val fragmentManager = supportFragmentManager
        when (string) {
            "Ai" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("Ai")).addToBackStack("Ai").commit()
            "2P" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("2P")).addToBackStack("2P").commit()
            "Signup" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, SignUpFragment()).commit()
            "Start" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, StartFragment()).addToBackStack("Start").commit()
            "History" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, HistoryFragment()).addToBackStack("History").commit()
        }
    }
}
