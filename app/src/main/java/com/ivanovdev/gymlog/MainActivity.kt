package com.ivanovdev.gymlog

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.view.WindowCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivanovdev.feature.screen.Screen
import com.ivanovdev.feature.screen.main.MainScreen
import com.ivanovdev.feature.screen.new_log.NewLogScreen
import com.ivanovdev.feature.screen.splash.SplashScreen
import com.ivanovdev.feature.ui.theme.GymLogTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymLogTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppNavHost(
                        activity = this
                    )
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    activity: Activity,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) { SplashScreen() }
        composable(Screen.Main.route) {
            MainScreen(navController)
            BackHandler(true) { }
        }
        composable(Screen.NewLog.route) {
            NewLogScreen(onBackClick = { navController.navigateUp() })
        }

        lifecycleOwner.lifecycleScope.launch {
            delay(20L)
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
            navController.navigate(Screen.Main.route)
        }
    }
}
