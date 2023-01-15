package com.ivanovdev.gymlog.screen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object WorkoutDetails : Screen("workout_details")
}