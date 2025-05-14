package com.francis.drivedeal.repository

import com.francis.drivedeal.data.UserDao
import com.francis.drivedeal.model.User


class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}