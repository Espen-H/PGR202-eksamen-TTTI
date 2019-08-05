package com.example.pgr202eksamen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "Score") val score: Int?
)