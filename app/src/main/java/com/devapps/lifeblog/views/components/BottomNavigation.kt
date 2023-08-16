package com.devapps.lifeblog.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devapps.lifeblog.ui.theme.Azure
import com.devapps.lifeblog.ui.theme.LifeblogTheme
import com.devapps.lifeblog.views.screens.AccountScreen
import com.devapps.lifeblog.views.screens.HomeScreen
import com.devapps.lifeblog.views.screens.NewsScreen

@Composable
fun BlogNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("news") {
            NewsScreen()
        }
        composable("account") {
            AccountScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
items: List<BottomNavItem>,
navController: NavHostController,
modifier: Modifier = Modifier,
onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.Gray,
        elevation = 5.dp
    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Azure,
                unselectedContentColor = Color.DarkGray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LifeblogTheme {
        NewsScreen()
    }
}