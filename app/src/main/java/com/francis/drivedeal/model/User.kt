package com.francis.drivedeal.model
import androidx.room.PrimaryKey
import androidx.room.Entity



@Entity(tableName = "buyer")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val email: String,
    val password: String,


)
