package com.francis.drivedeal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.francis.drivedeal.model.Car

@Database(entities = [Car::class], version = 2, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            // Check if the instance is already created
            return INSTANCE ?: synchronized(this) {
                // Create a new instance if it doesn't exist
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_db"
                )
                    .fallbackToDestructiveMigration()  // This will allow destructive migration
                    .build()

                INSTANCE = instance  // Save the instance to the singleton
                instance  // Return the instance
            }
        }
    }
}
