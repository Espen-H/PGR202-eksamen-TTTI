package com.example.pgr202eksamen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "Username") val userName: String,
    @ColumnInfo(name = "Score") var score: Int
)