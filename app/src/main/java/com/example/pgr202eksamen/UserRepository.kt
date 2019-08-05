package com.example.pgr202eksamen

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {

    val allUsersLive: LiveData<List<User>> = userDao.allUsersLive()

    @WorkerThread
    fun insert(user: User) {
        userDao.insert(user)
    }
}