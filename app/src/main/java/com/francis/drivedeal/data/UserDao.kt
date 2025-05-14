package com.francis.drivedeal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.francis.drivedeal.model.User


@Dao
interface UserDao {@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun registerUser(user: User)

    @Query("SELECT * FROM buyer WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): User?
}