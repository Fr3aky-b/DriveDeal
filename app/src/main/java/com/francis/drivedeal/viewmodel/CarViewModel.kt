package com.francis.drivedeal.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.francis.drivedeal.data.CarDatabase
import com.francis.drivedeal.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.io.copyTo
import kotlin.io.use

class CarViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val carDao = CarDatabase.getDatabase(app).carDao()

    val allProducts: LiveData<List<Car>> = carDao.getAllCars()

    fun addCar(name: String, price: Double,brand: String, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newProduct = Car(
                name = name,
                price = price,
                brand = brand,
                phone = phone,
                imagePath = savedImagePath
            )
            carDao.insertCar(newProduct)
        }
    }

    fun updateCar(updatedProduct: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            carDao.updateCar(updatedProduct)
        }
    }

    fun deleteCar(product: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteImageFromInternalStorage(product.imagePath)
            carDao.deleteCar(product)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}