package com.ivanovdev.gymlog.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ivanovdev.gymlog.R
import com.ivanovdev.gymlog.screen.NavigationItem
import com.ivanovdev.gymlog.screen.Screen
import com.ivanovdev.gymlog.screen.home.HomeScreen
import com.ivanovdev.gymlog.screen.logger.LoggerScreen
import com.ivanovdev.gymlog.screen.new_log.NewLogScreen
import com.ivanovdev.gymlog.screen.profile.ProfileScreen
import com.ivanovdev.gymlog.screen.statistic.StatisticScreen
import com.ivanovdev.gymlog.ui.theme.Primary
import com.ivanovdev.gymlog.ui.theme.PrimaryDark

@Composable
fun MainScreen(mainNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
//        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(mainNavController = mainNavController, navController = navController)
            }
        },
        backgroundColor = PrimaryDark // Set background color to avoid the white flashing when you switch between screens
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Statistic,
        NavigationItem.Home,
        NavigationItem.Logger,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = Primary,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.title)) },
                label = { Text(text = stringResource(id = item.title)) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(mainNavController: NavController, navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Statistic.route) {
            StatisticScreen()
        }
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Logger.route) {
            LoggerScreen(
                newLogClick = { mainNavController.navigate(Screen.NewLog.route) }
            )
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
//        composable(Screen.NewLog.route) {
//            NewLogScreen()
//        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = Primary,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}