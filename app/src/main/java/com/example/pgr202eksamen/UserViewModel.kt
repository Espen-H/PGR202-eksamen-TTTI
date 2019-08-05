package com.example.pgr202eksamen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val db:AppDatabase = AppDatabase.getInstance(application)
    internal val allUsers : LiveData<List<User>> = db.userDao().getAll()

    fun insert(user:User){
        db.userDao().insert(user)
    }
}