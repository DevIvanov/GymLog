package com.ivanovdev.feature.screen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object NewLog : Screen("new_log")
    object WorkoutDetails : Screen("workout_details")
}