package com.example.pgr202eksamen

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM Users ORDER BY score DESC")
    fun allUsersLive(): LiveData<List<User>>

    @Query("SELECT * FROM Users ORDER BY score DESC")
    fun allUsers(): List<User>

    @Query("SELECT * FROM Users WHERE Username LIKE :username LIMIT 1")
    fun findByName(username: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(User: User)

    @Update
    fun update(username: User)

    @Delete
    fun delete(username: User)

}