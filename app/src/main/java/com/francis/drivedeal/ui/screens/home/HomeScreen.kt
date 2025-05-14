package com.francis.drivedeal.ui.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francis.drivedeal.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.francis.drivedeal.navigation.ROUT_ABOUT
import com.francis.drivedeal.navigation.ROUT_ADD_PRODUCT
import com.francis.drivedeal.navigation.ROUT_ADMIN_PRODUCT_LIST
import com.francis.drivedeal.navigation.ROUT_REGISTER
import com.francis.drivedeal.navigation.ROUT_USER_DASHBOARD
import com.francis.drivedeal.ui.screens.userdashboard.newblue
import com.francis.drivedeal.ui.theme.newwhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val deepBlue = newwhite
    val accentColor = Color(0xFF030302)
    val softWhite = Color(0xFFECEFF1)
    val softblue = newblue
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "DriveDeal",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = accentColor,
                        fontFamily = FontFamily.Cursive
                    )
                },
                navigationIcon = {
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = accentColor
                            )
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Register") },
                                onClick = {
                                    navController.navigate(ROUT_REGISTER)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("About Us") },
                                onClick = {
                                    navController.navigate(ROUT_ABOUT)
                                    menuExpanded = false
                                }
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = accentColor)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = accentColor)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = deepBlue
                )
            )
        },
        containerColor = deepBlue
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
                .background(deepBlue)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Search for more Cars...") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = softWhite,
                    unfocusedContainerColor = softWhite
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Promo card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(softblue),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("🔥 Get 50% Off Today!", fontSize = 22.sp, color = accentColor, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Make your dream come true with unbeatable car prices!",
                        fontSize = 14.sp,
                        color = softWhite,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Why choose DriveDeal?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We offer top luxury brands:",
                fontSize = 16.sp,
                color = softWhite,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val imageItems = listOf(
                Pair(R.drawable.bentl, "Bentley"),
                Pair(R.drawable.benz, "Mercedes"),
                Pair(R.drawable.bima, "BMW"),
                Pair(R.drawable.cadillac, "Cadillac"),
                Pair(R.drawable.lambo4, "Lamborghini"),
                Pair(R.drawable.rolls, "Rolls Royce")
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(imageItems) { item ->
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(160.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Box {
                            Image(
                                painter = painterResource(id = item.first),
                                contentDescription = item.second,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.4f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.second,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buyer & Seller Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .clickable { navController.navigate(ROUT_USER_DASHBOARD) }
                        .weight(1f)
                        .height(120.dp),
                    colors = CardDefaults.cardColors(softblue),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Buyer", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = deepBlue)
                        Text("Explore luxury cars", fontSize = 12.sp, color = deepBlue)
                    }
                }

                Card(
                    modifier = Modifier
                        .clickable { navController.navigate(ROUT_ADD_PRODUCT) }
                        .weight(1f)
                        .height(120.dp),
                    colors = CardDefaults.cardColors(softblue),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Seller", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = deepBlue)
                        Text("List your car today", fontSize = 12.sp, color = deepBlue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Explore Categories
            Text(
                text = "Explore Categories",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            val categories = listOf("Electric", "SUV", "Convertible", "Classic", "Sport", "Family")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    Card(
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(newblue),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = category,
                            color = accentColor,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Testimonials
            Text(
                text = "What Our Users Say",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val testimonials = listOf(
                "Absolutely love the UI and car selection. Made buying my BMW super easy." to "James T.",
                "Very smooth experience. I was able to list and sell my car in 2 days!" to "Amina R.",
                "DriveDeal saved me thousands. Best marketplace for exotic cars." to "Daniel K."
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                testimonials.forEach { (quote, author) ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = newblue),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "\"$quote\"", fontStyle = FontStyle.Italic, color = Color.DarkGray)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "- $author", fontWeight = FontWeight.Bold, color = accentColor)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Contact Us
            Text(
                text = "Contact Us",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = newblue),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)) {
                    Text("📞 Phone: +254 759891750", color = Color.White, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("✉️ Email: support@drivedeal.com", color = Color.White, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🌐 Website: www.drivedeal.com", color = Color.White, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // About Us Button
            Button(
                onClick = { navController.navigate(ROUT_ABOUT) },
                colors = ButtonDefaults.buttonColors(containerColor = softblue),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Learn More About Us", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
