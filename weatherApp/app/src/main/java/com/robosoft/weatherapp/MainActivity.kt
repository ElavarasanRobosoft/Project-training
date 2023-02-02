package com.robosoft.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robosoft.weatherapp.ui.home.HomeContent
import com.robosoft.weatherapp.navigationdrawer.AppBar
import com.robosoft.weatherapp.navigationdrawer.DrawerBody
import com.robosoft.weatherapp.navigationdrawer.DrawerHeader
import com.robosoft.weatherapp.navigationdrawer.MenuItem
import com.robosoft.weatherapp.ui.favourite.Favourite
import com.robosoft.weatherapp.ui.recent.Recent
import com.robosoft.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash_Screen") {
        composable("Splash_Screen") {
            SplashScreen(navController = navController)
        }
        composable("Home_Screen") {
            HomeScreen(navController = navController)
        }
        composable("Favourite_screen"){
            Favourite(navController = navController)
        }
        composable("Recent_screen"){
            Recent(navController = navController)
        }
    }
}


@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("Home_Screen")
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background_android),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = "Splash screen logo"
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Box {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.background_android),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Background"
        )
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            topBar = {
                AppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                DrawerHeader()
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "home",
                            title = "Home",
                            contentDescription = "Go to home screen",
                            icon = Icons.Default.Home
                        ),
                        MenuItem(
                            id = "favourite",
                            title = "Favourite",
                            contentDescription = "Go to favourite screen",
                            icon = Icons.Default.Star
                        ),
                        MenuItem(
                            id = "recent",
                            title = "Recent Screen",
                            contentDescription = "Go to recent screen",
                            icon = Icons.Default.Search
                        )
                    ), onItemClick = {
                        when (it.id) {
                            "home" -> navController.navigate("Home_Screen")
                            "favourite" -> navController.navigate("Favourite_screen")
                            "recent" -> navController.navigate("Recent_screen")
                        }
                    }
                )
            })
        {
            HomeContent()
        }
    }
}


