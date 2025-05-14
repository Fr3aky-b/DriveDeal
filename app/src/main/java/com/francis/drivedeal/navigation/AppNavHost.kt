package com.francis.drivedeal.navigation



import RollsScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.francis.drivedeal.data.UserDatabase
import com.francis.drivedeal.repository.UserRepository

import com.francis.drivedeal.ui.screens.about.AboutScreen
import com.francis.drivedeal.ui.screens.auth.LoginScreen
import com.francis.drivedeal.ui.screens.auth.RegisterScreen
import com.francis.drivedeal.ui.screens.cars.BMWScreen
import com.francis.drivedeal.ui.screens.cars.BentleyScreen
import com.francis.drivedeal.ui.screens.cars.LamboScreen
import com.francis.drivedeal.ui.screens.cars.MercedesScreen

import com.francis.drivedeal.ui.screens.home.HomeScreen
import com.francis.drivedeal.ui.screens.products.AddProductScreen
import com.francis.drivedeal.ui.screens.products.ProductListScreen
import com.francis.drivedeal.ui.screens.splash.SplashScreen
import com.francis.drivedeal.viewmodel.AuthViewModel
import com.francis.drivedeal.viewmodel.CarViewModel
import com.francis.drivedeal.ui.screens.products.EditProductScreen
import com.francis.drivedeal.ui.screens.seller.AdminProductListScreen
import com.francis.drivedeal.ui.screens.userdashboard.UserDashboardScreen
import com.francis.drivedeal.ui.screens.userdashboard.CarDashboardScreen



@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String=ROUT_SPLASH,
    carViewModel: CarViewModel=viewModel(),

    ) {
    val context = LocalContext.current
    // Initialize Room Database and Repository for Authentication
    val appDatabase = UserDatabase.getDatabase(context)
    val authRepository = UserRepository(appDatabase.userDao())
    val authViewModel: AuthViewModel = AuthViewModel(authRepository)


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier

    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_USER_DASHBOARD) {
            UserDashboardScreen(navController)
        }
        composable(ROUT_CAR_DASHBOARD) {
            CarDashboardScreen(navController)
        }
        composable(ROUT_BENTLEY) {
            BentleyScreen(navController)
        }
        composable(ROUT_BMW) {
            BMWScreen(navController)
        }
        composable(ROUT_LAMBO) {
            LamboScreen(navController)
        }
        composable(ROUT_MERCEDES) {
            MercedesScreen(navController)
        }
        composable(ROUT_ROLLS) {
            RollsScreen(navController)
        }
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
        // PRODUCTS
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, carViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, carViewModel)
        }
        composable(ROUT_ADMIN_PRODUCT_LIST) {
            AdminProductListScreen(navController, carViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController,carViewModel)
            }
        }





    }


}



