package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = if (isOnBoard(context)) Home.route else Onboarding.route
    ) {
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}

fun isOnBoard(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(FIRST_NAME, "") ?: ""
    val lastName = sharedPreferences.getString(LAST_NAME, "") ?: ""
    val email = sharedPreferences.getString(EMAIL, "") ?: ""

    return !(firstName.isBlank() || lastName.isBlank() || email.isBlank())
}