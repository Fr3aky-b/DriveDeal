package com.francis.drivedeal.ui.screens.cars


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.tooling.preview.Preview
import com.francis.drivedeal.R
import com.francis.drivedeal.navigation.ROUT_CAR_DASHBOARD
import com.francis.drivedeal.navigation.ROUT_USER_DASHBOARD
import com.francis.drivedeal.ui.screens.userdashboard.newblue
import com.francis.drivedeal.ui.screens.userdashboard.newwhite
import com.francis.drivedeal.ui.theme.newblack

// Data model
data class BentleyCar(
    val name: String,
    val price: String,
    val imageRes: Int,
    val phoneNumber: String
)

// Sample data
val bentleyList = listOf(
    BentleyCar("Bentley Bentayga", "$177,000", R.drawable.bentley1, "1234567890"),
    BentleyCar("Bentley Continental GT", "$202,500", R.drawable.bentley2, "2345678901"),
    BentleyCar("Bentley Flying Spur", "$198,000", R.drawable.bentley3, "3456789012"),
    BentleyCar("Bentley Mulsanne", "$310,800", R.drawable.bentley4, "4567890123"),
    BentleyCar("Bentley Azure", "$250,000", R.drawable.bentley5, "5678901234")
)

// Main screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BentleyScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bentley Collection", color = newblack) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_USER_DASHBOARD)
                    {popUpTo(ROUT_CAR_DASHBOARD) { inclusive = true }} }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = newblue)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(newwhite)
        ) {
            items(bentleyList) { car ->
                BentleyCarCard(
                    car = car,
                    onMessageDealer = {
                        val uri = Uri.parse("sms:${car.phoneNumber}")
                        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                            putExtra("sms_body", "Hello, I'm interested in the ${car.name}")
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                    },
                    onPayNow = {
                        navController.navigate("purchase/${car.name}/${car.price}")
                    }
                )
            }
        }
    }
}

// Car card composable
@Composable
fun BentleyCarCard(
    car: BentleyCar,
    onMessageDealer: () -> Unit,
    onPayNow: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = car.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(car.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(car.price, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onMessageDealer,
                    colors = ButtonDefaults.buttonColors(newblue),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Message Dealer", color = Color.White)
                }
                Button(
                    onClick = onPayNow,
                    colors = ButtonDefaults.buttonColors(newblue),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pay Now", color = Color.White)
                }
            }
        }
    }
}

// Purchase screen
@Composable
fun PurchaseScreen1(carName: String?, carPrice: String?) {
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
            colors = ButtonDefaults.buttonColors(newblue)
        ) {
            Text("Confirm Purchase", color = Color.White)
        }
    }
}

// Navigation
@Composable
fun AppNavGraph1(navController: NavHostController) {
    NavHost(navController, startDestination = "bentley") {
        composable("bentley") {
            BentleyScreen(navController)
        }
        composable(
            "purchase/{carName}/{carPrice}",
            arguments = listOf(
                navArgument("carName") { defaultValue = "Bentley" },
                navArgument("carPrice") { defaultValue = "$0.00" }
            )
        ) { backStackEntry ->
            val carName = backStackEntry.arguments?.getString("carName")
            val carPrice = backStackEntry.arguments?.getString("carPrice")
            PurchaseScreen(carName, carPrice)
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun BentleyScreenPreview() {
    BentleyScreen(navController = rememberNavController())
}
