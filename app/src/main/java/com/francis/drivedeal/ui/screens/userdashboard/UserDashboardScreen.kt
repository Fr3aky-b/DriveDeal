package com.francis.drivedeal.ui.screens.userdashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.francis.drivedeal.R
import com.francis.drivedeal.navigation.ROUT_ADMIN_PRODUCT_LIST
import com.francis.drivedeal.navigation.ROUT_CAR_DASHBOARD
import com.francis.drivedeal.navigation.ROUT_HOME
import com.francis.drivedeal.ui.theme.newwhite

@Composable
fun UserDashboardScreen(navController: NavController) {
    val cardColor = Color(0xFF1976D2)
    val backgroundColor = newwhite

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Arrow
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate(ROUT_HOME)
                    {popUpTo(ROUT_CAR_DASHBOARD) { inclusive = true }} },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

            // Decorative Text
            Text(
                text = "Welcome to DriveDeal",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            Text(
                text = "Find your perfect car or explore the latest arrivals with just a tap.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF666666),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cards
            DashboardCard(
                title = "Explore Cars",
                iconRes = R.drawable.explore,
                backgroundColor = cardColor
            ) {
                navController.navigate(ROUT_CAR_DASHBOARD)
            }

            DashboardCard(
                title = "Other Cars",
                iconRes = R.drawable.others,
                backgroundColor = cardColor
            ) {
                navController.navigate(ROUT_ADMIN_PRODUCT_LIST)
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    iconRes: Int,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDashboardPreview() {
    UserDashboardScreen(navController = rememberNavController())
}
