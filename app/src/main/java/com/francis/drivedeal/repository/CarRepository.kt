package com.francis.drivedeal.repository


import android.content.Context
import com.francis.drivedeal.data.CarDatabase
import com.francis.drivedeal.model.Car


class CarRepository(context: Context) {
    private val carDao = CarDatabase.getDatabase(context).carDao()

    suspend fun insertCar(product: Car) {
        carDao.insertCar(product)
    }

    fun getAllCars() = carDao.getAllCars()

    suspend fun deleteCar(product: Car) = carDao.deleteCar(product)
}