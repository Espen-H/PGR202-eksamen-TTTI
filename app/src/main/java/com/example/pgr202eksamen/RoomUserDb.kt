package com.example.pgr202eksamen

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class RoomUserDb : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var INSTANCE: RoomUserDb? = null

        fun getDatabase(context: Context): RoomUserDb {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomUserDb::class.java,
                    "Person_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

