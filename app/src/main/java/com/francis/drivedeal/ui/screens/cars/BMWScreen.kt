package com.francis.drivedeal.ui.screens.cars
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.annotation.DrawableRes

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.res.painterResource
import com.francis.drivedeal.R
import androidx.navigation.*
import androidx.navigation.compose.*
import com.francis.drivedeal.navigation.ROUT_CAR_DASHBOARD
import com.francis.drivedeal.ui.theme.newblack
import com.francis.drivedeal.ui.theme.newblue
import com.francis.drivedeal.ui.theme.newwhite


// Data class using drawable resource
data class BMWCar(val name: String, val price: String, @DrawableRes val imageRes: Int, val phone: String)

// Local drawable images
val bmwList = listOf(
    BMWCar("BMW M4 Competition", "$78,800", R.drawable.bmw1, "1234567890"),
    BMWCar("BMW X7 M60i", "$103,100", R.drawable.bmw2, "1234567891"),
    BMWCar("BMW i8 Roadster", "$147,500", R.drawable.bmw3, "1234567892"),
    BMWCar("BMW 7 Series", "$96,400", R.drawable.bmw4, "1234567893"),
    BMWCar("BMW Z4 M40i", "$66,300", R.drawable.bww5, "1234567894")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMWScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMW Collection", color = newblack) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUT_CAR_DASHBOARD)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = newblue),
                modifier = Modifier.background(newblue

                )
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(newwhite)
        ) {
            items(bmwList) { car ->
                BMWCarCard(car = car, navController = navController)
            }
        }
    }
}

@Composable
fun BMWCarCard(car: BMWCar, navController: NavController) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = car.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = car.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = car.price, fontSize = 18.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {
                        val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("smsto:${car.phone}")
                            putExtra("sms_body", "Hello Dealer, I'm interested in your ${car.name}.")
                        }
                        if (smsIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(smsIntent)
                        }


                    },
                    colors = ButtonDefaults.buttonColors(newblue)

                ) {
                    Text("Message Dealer", color = Color.White)
                }

                Button(
                    onClick = {
                        navController.navigate("purchase/${car.name}/${car.price}")
                    },
                    colors = ButtonDefaults.buttonColors(newblue)
                ) {
                    Text("Buy Now", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PurchaseScreen(carName: String?, carPrice: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Confirm Your Purchase", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Car: ${carName ?: "Unknown"}", fontSize = 18.sp)
        Text("Price: ${carPrice ?: "N/A"}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                println("Order placed for $carName at $carPrice")
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF34C759))
        ) {
            Text("Confirm Purchase", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMWScreenPreview() {
    BMWScreen(navController = rememberNavController())
}

// App navigation graph
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "bmw") {
        composable("bmw") {
            BMWScreen(navController)
        }

        composable(
            "purchase/{carName}/{carPrice}",
            arguments = listOf(
                navArgument("carName") { defaultValue = "BMW" },
                navArgument("carPrice") { defaultValue = "$0.00" }
            )
        ) { backStackEntry ->
            val carName = backStackEntry.arguments?.getString("carName")
            val carPrice = backStackEntry.arguments?.getString("carPrice")
            PurchaseScreen(carName, carPrice)
        }
    }
}
