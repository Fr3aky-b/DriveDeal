package com.francis.drivedeal.ui.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.francis.drivedeal.R
import coil.compose.rememberAsyncImagePainter
import com.francis.drivedeal.navigation.ROUT_ADD_PRODUCT
import com.francis.drivedeal.navigation.ROUT_PRODUCT_LIST
import com.francis.drivedeal.ui.theme.newwhite

import com.francis.drivedeal.viewmodel.CarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(productId: Int?, navController: NavController, viewModel: CarViewModel) {
    val context = LocalContext.current
    val productList by viewModel.allProducts.observeAsState(emptyList())
    val product = remember(productList) { productList.find { it.id == productId }}
    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(product?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Car") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
                            onClick = {
                                navController.navigate(ROUT_PRODUCT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Car") },
                            onClick = {
                                navController.navigate(ROUT_ADD_PRODUCT)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->

        // Background Image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(newwhite)

        ) {
            Image(
                painter = painterResource(id = R.drawable.home), // <-- Replace with your image
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Background Image

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (product != null) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Product Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text("Car Price") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                        contentDescription = "Car Image",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(8.dp)
                    )

                    Button(
                        onClick = { imagePicker.launch("image/*") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp, end = 40.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray)
                    ) {
                        Text("Change Image")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val updatedPrice = price.toDoubleOrNull()
                            if (updatedPrice != null) {
                                viewModel.updateCar(product.copy(name = name, price = updatedPrice, imagePath = imagePath))
                                Toast.makeText(context, "Car Updated!", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(context, "Invalid price entered!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp, end = 40.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Update Car")
                    }
                } else {
                    Text(text = "Car not found", color = MaterialTheme.colorScheme.error)
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Go Back")
                    }
                }
            }
        }
    }
}
