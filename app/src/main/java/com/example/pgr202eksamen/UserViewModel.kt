package com.example.pgr202eksamen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val  repository: UserRepository
    val allUsers: LiveData<List<User>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val userDao = RoomUserDb.getDatabase(application.applicationContext)
            .userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsersLive
    }

    fun insert(user: User) = scope.launch(Dispatchers.IO) {
        repository.insert(user)
    }
    fun getUser(username: String):User {
       return repository.getUser(username)
    }

}