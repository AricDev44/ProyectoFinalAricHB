package com.example.proyectofinalarichb.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalarichb.View.CloseRestaurantsView
import com.example.proyectofinalarichb.View.DishView
import com.example.proyectofinalarichb.View.HomeView
import com.example.proyectofinalarichb.View.LoginView
import com.example.proyectofinalarichb.View.NutrientTrackerView
import com.example.proyectofinalarichb.View.PremiumView
import com.example.proyectofinalarichb.View.RegisterView
import com.example.proyectofinalarichb.View.RoutineView
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){

    val startRoute = if(FirebaseAuth.getInstance().currentUser != null){
        Routes.Home.route
    }else{
        Routes.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startRoute,
        modifier = modifier
    ) {
        composable(Routes.Login.route) {
            LoginView(
                onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Register.route) {
            RegisterView(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home.route) {
            HomeView(
                onItemSelected = { index ->
                    navigateByBar(navController, index)
                },
                onPremiumButtonClick = {
                    navController.navigate(Routes.Premium.route)
                },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Dish.route) {
            DishView(
                onItemSelected = { index ->
                    navigateByBar(navController, index)
                },
                onPremiumButtonClick = {
                    navController.navigate(Routes.Premium.route)
                }
            )
        }

        composable(Routes.NutrientTracker.route) {
            NutrientTrackerView(
                onItemSelected = { index ->
                    navigateByBar(navController, index)
                },
                onPremiumButtonClick = {
                    navController.navigate(Routes.Premium.route)
                }
            )
        }

        composable(Routes.CloseRestaurants.route) {
            CloseRestaurantsView(
                onItemSelected = { index ->
                    navigateByBar(navController, index)
                },
                onPremiumButtonClick = {
                    navController.navigate(Routes.Premium.route)
                }
            )
        }

        composable(Routes.Premium.route) {
            PremiumView(
                onNavigateToRoutines = {
                    navController.navigate(Routes.Routine.route)
                },
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Routine.route) {
            RoutineView(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}

fun navigateByBar(navController: NavHostController, index: Int){
    when (index){
        0 -> navController.navigate(Routes.Home.route) {
            popUpTo(Routes.Home.route) { inclusive = true }
        }
        1 -> navController.navigate(Routes.Dish.route)
        2 -> navController.navigate(Routes.NutrientTracker.route)
        3 -> navController.navigate(Routes.CloseRestaurants.route)
    }
}