package com.example.pgr202eksamen

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAll()

    @WorkerThread
    fun insert(user: User) {
        userDao.insert(user)
    }
}