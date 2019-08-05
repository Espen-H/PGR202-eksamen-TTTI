package com.example.pgr202eksamen

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var users: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        users = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users"
        ).build()
        if (!sharedPref.contains("active_player")) {
            replaceFragment("Signup")
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

    // Inserts user into the database
    fun instertUserInfo(user: String) {
        val newUser: User = User(0, user,0)
        users.userDao().insert(newUser)
    }

    // updates the user in the database
    fun updateUserScore(user: User) {
        users.userDao().update(user)
    }



    fun replaceFragment(string: String) {
        val fragmentManager = supportFragmentManager
        when (string) {
            "Ai" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("Ai")).commit()
            "2P" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, GameFragment("2P")).commit()
            "Signup" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, SignUpFragment()).commit()
            "Start" -> fragmentManager.beginTransaction().replace(R.id.fragmentHolder, StartFragment()).commit()
        }
    }
}
