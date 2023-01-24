package com.ivanovdev.feature.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ivanovdev.feature.R

sealed class NavigationItem(
    val route: String,
    @DrawableRes
    var icon: Int,
    @StringRes
    var title: Int
) {
    object Statistic : NavigationItem("statistic", R.drawable.ic_statistic, R.string.title_statistic)
    object Home : NavigationItem("home", R.drawable.ic_home, R.string.title_home)
    object Empty : NavigationItem("home", R.drawable.ic_home, R.string.title_home)
    object Logger : NavigationItem("logger", R.drawable.ic_logger, R.string.title_logger)
    object Profile : NavigationItem("profile", R.drawable.ic_profile, R.string.title_profile)
}