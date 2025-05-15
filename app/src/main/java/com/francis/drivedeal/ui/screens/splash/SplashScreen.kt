package com.francis.drivedeal.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.francis.drivedeal.R
import com.francis.drivedeal.navigation.ROUT_HOME
import com.francis.drivedeal.navigation.ROUT_REGISTER
import com.francis.drivedeal.ui.theme.newblack
import com.francis.drivedeal.ui.theme.newblue
import com.francis.drivedeal.ui.theme.newwhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.0f) }

    // Handle animation and navigation
    LaunchedEffect(Unit) {
        visible = true
        while (progress < 1.0f) {
            delay(400)
            progress += 0.2f
        }
        delay(1000)
        navController.navigate(ROUT_HOME) {
            popUpTo(0) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(newwhite)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.login), // Replace with actual logo
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )

                // App Name
                Text(
                    text = "DriveDeal",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = newblue
                )

                // Tagline
                Text(
                    text = "Your best online car selling shop",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Cursive,
                    color = newblack
                )

                // Progress Bar
                LinearProgressIndicator(
                    progress = progress,
                    color = newblue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .padding(top = 24.dp)
                )

                // Extra Info
                Spacer(modifier = Modifier.height(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "• List and sell cars easily",
                        fontSize = 18.sp,
                        color = newblack
                    )
                    Text(
                        text = "• Connect with verified buyers",
                        fontSize = 18.sp,
                        color = newblack
                    )
                    Text(
                        text = "• Explore latest models and offers",
                        fontSize = 18.sp,
                        color = newblack
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
