package com.example.pgr202eksamen

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.net.UnknownServiceException

class UserRepository (private val userDao: UserDao) {

    val allUsersLive: LiveData<List<User>> = userDao.allUsersLive()

    @WorkerThread
    fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    fun update(user: User) {
        userDao.update(user)
    }

    @WorkerThread
    fun getUser(username: String): User {
        return userDao.findByName(username)
    }

    @WorkerThread
    fun getUsers(): List<User> {
        return userDao.allUsers()
    }
}