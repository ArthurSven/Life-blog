package com.devapps.lifeblog

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.lifeblog.ui.theme.LifeblogTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devapps.lifeblog.views.components.BlogNavigation
import com.devapps.lifeblog.views.components.BottomNavItem
import com.devapps.lifeblog.views.components.BottomNavigationBar
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material.Surface(color = Color(0xffffffff), modifier = Modifier
                .fillMaxSize()) {
                Navigation()
            }
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splashscreen") {
        composable("splashscreen") {
            SplashScreen(navController = navController)
        }

        composable("homescreen") {
            val navController1 = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Home",
                                route = "home",
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                name = "News",
                                route = "news",
                                icon = Icons.Default.List
                            ),
                            BottomNavItem(
                                name = "Account",
                                route = "account",
                                icon = Icons.Default.Person
                            )
                        ),
                        navController = navController1,
                        onItemClick = {
                            navController1.navigate(it.route)
                        })
                }
            ) {
                    BlogNavigation(navController = navController1)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("homescreen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(azureColor())
    ) {
        Text(
            text = "LIFE BLOG",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    }
}



@Composable
fun azureColor(): Color {
    return Color(0xFF00FFFF) // Replace with your custom color value
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LifeblogTheme {

    }
}