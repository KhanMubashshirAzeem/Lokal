package com.mubashshir.lokal.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mubashshir.lokal.ui.navigation.LokalNavHost
import com.mubashshir.lokal.ui.navigation.Screen
import com.mubashshir.lokal.ui.theme.Dimens

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    val showBottomBar = Screen.bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens.MiniPlayerHeight)
                    )
                    BottomNavigationBar(navController = navController)
                }
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        LokalNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        Screen.bottomNavItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val icon = if (currentRoute == screen.route) screen.filledIcon else screen.outlinedIcon
                    icon?.let { Icon(imageVector = it, contentDescription = screen.label) }
                },
                label = { Text(text = screen.label) }
            )
        }
    }
}



