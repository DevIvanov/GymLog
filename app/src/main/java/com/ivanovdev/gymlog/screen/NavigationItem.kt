package com.ivanovdev.gymlog.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ivanovdev.gymlog.R

sealed class NavigationItem(
    val route: String,
    @DrawableRes
    var icon: Int,
    @StringRes
    var title: Int
) {
    object Home : NavigationItem("home", R.drawable.ic_home, R.string.title_home)
    object Logger : NavigationItem("logger", R.drawable.ic_logger, R.string.title_logger)
    object Profile : NavigationItem("profile", R.drawable.ic_profile, R.string.title_profile)
}