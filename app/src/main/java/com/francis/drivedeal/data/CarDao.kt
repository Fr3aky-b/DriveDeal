package com.francis.drivedeal.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.francis.drivedeal.model.Car




@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>

    @Insert
    suspend fun insertCar(product: Car)

    @Update
    suspend fun updateCar(product: Car)

    @Delete
    suspend fun deleteCar(product: Car)
}