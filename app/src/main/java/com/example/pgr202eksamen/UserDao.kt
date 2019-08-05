package com.example.pgr202eksamen

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY Score DESC")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE userName LIKE :username LIMIT 1")
    fun findByName(username: String): User

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(User: User)

    @Update
    fun update(User: User)

    @Delete
    fun delete(User: User)
}