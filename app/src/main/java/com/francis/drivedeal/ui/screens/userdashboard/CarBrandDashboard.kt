package com.francis.drivedeal.ui.screens.userdashboard

import RollsScreen
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.francis.drivedeal.R
import androidx.compose.foundation.lazy.grid.*

import androidx.navigation.compose.rememberNavController
import com.francis.drivedeal.ui.screens.home.HomeScreen
import com.francis.drivedeal.ui.theme.newblue
import com.francis.drivedeal.ui.theme.newwhite
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import com.francis.drivedeal.navigation.ROUT_BENTLEY
import com.francis.drivedeal.navigation.ROUT_BMW
import com.francis.drivedeal.navigation.ROUT_CAR_DASHBOARD
import com.francis.drivedeal.navigation.ROUT_LAMBO
import com.francis.drivedeal.navigation.ROUT_MERCEDES
import com.francis.drivedeal.navigation.ROUT_ROLLS
import com.francis.drivedeal.navigation.ROUT_USER_DASHBOARD
import com.francis.drivedeal.ui.screens.cars.BMWScreen
import com.francis.drivedeal.ui.screens.cars.BentleyScreen
import com.francis.drivedeal.ui.screens.cars.LamboScreen
import com.francis.drivedeal.ui.screens.cars.MercedesScreen

import kotlinx.coroutines.delay



import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.francis.drivedeal.navigation.ROUT_HOME


val newblue = Color(0xFF1976D2)
val newwhite = Color(0xFFF9F9F9)

data class CarBrand(val name: String, val logoResId: Int)

// Constants for route names (ensure these match your app's routes)
const val ROUT_USER_DASHBOARD = "userdashboard"
const val ROUT_CAR_DASHBOARD = "cardashboard"
const val ROUT_BMW = "bima"
const val ROUT_MERCEDES = "benz"
const val ROUT_LAMBO = "lambo"
const val ROUT_ROLLS = "rolls"
const val ROUT_BENTLEY = "bentley"
const val ROUT_CADILLAC = "cadillac"




@Composable
fun CarDashboardScreen(navController: NavController) {
    val carBrands = listOf(
        CarBrand("BMW", R.drawable.logo1),
        CarBrand("Mercedes", R.drawable.logo4),
        CarBrand("Lamborghini", R.drawable.logo5),
        CarBrand("Rolls-Royce", R.drawable.logo6),
        CarBrand("Bentley", R.drawable.logo9),
        CarBrand("Cadillac", R.drawable.logo2),
    )

    val carouselTexts = listOf(
        "Welcome to DriveDeal!",
        "Explore the most elite car brands.",
        "Find your dream car effortlessly."
    )

    var carouselIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            carouselIndex = (carouselIndex + 1) % carouselTexts.size
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(newwhite)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }

            Text(
                text = carouselTexts[carouselIndex],
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Discover Elite Car Brands",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                itemsIndexed(carBrands) { index, brand ->
                    BrandCardModern(brand = brand, index = index) {
                        when (brand.name) {
                            "BMW" -> navController.navigate(ROUT_BMW)
                            "Mercedes" -> navController.navigate(ROUT_MERCEDES)
                            "Lamborghini" -> navController.navigate(ROUT_LAMBO)
                            "Rolls-Royce" -> navController.navigate(ROUT_ROLLS)
                            "Bentley" -> navController.navigate(ROUT_BENTLEY)
                            "Cadillac" -> navController.navigate(ROUT_CADILLAC)
                        }
                    }
                }
            }
        }

        // Floating bottom action bar

    }
}

@Composable
fun BrandCardModern(brand: CarBrand, index: Int, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 100L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = brand.logoResId),
                contentDescription = "${brand.name} logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
                            startY = 300f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = brand.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CarAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUT_CAR_DASHBOARD) {
        composable(ROUT_CAR_DASHBOARD) { CarDashboardScreen(navController) }
        composable(ROUT_BMW) { BMWScreen(navController) }
        composable(ROUT_MERCEDES) { MercedesScreen(navController) }
        composable(ROUT_LAMBO) { LamboScreen(navController) }
        composable(ROUT_ROLLS) { RollsScreen(navController) }
        composable(ROUT_BENTLEY) { BentleyScreen(navController) }
         }
    }


@Preview(showBackground = true)
@Composable
fun CarDashboardPreview() {
    CarDashboardScreen(navController = rememberNavController())
}
