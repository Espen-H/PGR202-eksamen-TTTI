package com.example.pgr202eksamen

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE userName LIKE :username LIMIT 1")
    fun findByName(username: String): User

    @Insert
    fun insert(User: User)

    @Update
    fun update(User: User)

    @Delete
    fun delete(User: User)
}