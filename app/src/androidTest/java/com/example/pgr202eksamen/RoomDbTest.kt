package com.example.pgr202eksamen

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

class RoomDbTest {
    class EntityReadWriteTest {
        private lateinit var userDao: UserDao
        private lateinit var db: RoomUserDb

        @Before
        fun createDb() {
            val context: Context = ApplicationProvider.getApplicationContext()
            db = Room.inMemoryDatabaseBuilder(
                context, RoomUserDb::class.java
            ).build()
            userDao = db.userDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun writeUserAndReadInList() {
            val user = User("test", 1)
            userDao.insert(user)
            val userItem = userDao.findByName(user.userName)
            assertThat(userItem, equalTo(user))
        }
    }
}
