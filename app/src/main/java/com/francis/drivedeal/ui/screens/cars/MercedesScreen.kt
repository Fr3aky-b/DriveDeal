package com.francis.drivedeal.ui.screens.cars
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.francis.drivedeal.R
import com.francis.drivedeal.navigation.ROUT_CAR_DASHBOARD
import com.francis.drivedeal.ui.theme.newblack
import com.francis.drivedeal.ui.theme.newblue
import com.francis.drivedeal.ui.theme.newwhite

// 1. Data class for a Mercedes car
data class MercedesCar(
    val name: String,
    val price: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MercedesScreen(navController: NavController) {
    val context = LocalContext.current

    // Dialog control states
    var showPaymentDialog by remember { mutableStateOf(false) }
    var selectedCarName by remember { mutableStateOf("") }

    // Car list
    val mercedesCars = listOf(
        MercedesCar("Mercedes-Benz S-Class", "$110,000", R.drawable.sclass),
        MercedesCar("Mercedes-Benz GLE", "$75,000", R.drawable.gle),
        MercedesCar("Mercedes-Benz C-Class", "$50,000", R.drawable.cclass),
        MercedesCar("Mercedes-Benz AMG GT", "$130,000", R.drawable.gt),
        MercedesCar("Mercedes-Benz GLS", "$95,000", R.drawable.gls)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mercedes Collection", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick =
                        { navController.navigate(ROUT_CAR_DASHBOARD){
                            popUpTo(ROUT_CAR_DASHBOARD) { inclusive = true }}
                            }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = newblue,
                    titleContentColor = newblack,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = newwhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            mercedesCars.forEach { car ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = car.imageRes),
                            contentDescription = car.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = car.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = car.price,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("smsto:123456789") // Replace with actual dealer number
                                        putExtra("sms_body", "Hi, I'm interested in the ${car.name}")
                                    }
                                    context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = newblue)
                            ) {
                                Text("Message Dealer", color = newblack)
                            }
                            Button(
                                onClick = {
                                    selectedCarName = car.name
                                    showPaymentDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = newblue)
                            ) {
                                Text("Buy Now", color = newblack)
                            }
                        }
                    }
                }
            }
        }

        // Payment Confirmation Dialog
        if (showPaymentDialog) {
            AlertDialog(
                onDismissRequest = { showPaymentDialog = false },
                title = { Text("Confirm Purchase") },
                text = { Text("Do you want to proceed with the payment for $selectedCarName?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showPaymentDialog = false
                            Toast.makeText(
                                context,
                                "Payment for $selectedCarName successful!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    ) {
                        Text("Pay")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showPaymentDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MercedesScreenPreview() {
    MercedesScreen(navController = rememberNavController())
}
