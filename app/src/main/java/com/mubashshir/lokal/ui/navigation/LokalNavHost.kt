package com.mubashshir.lokal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mubashshir.lokal.ui.screens.FavoritesScreen
import com.mubashshir.lokal.ui.screens.HomeScreen
import com.mubashshir.lokal.ui.screens.PlayerScreen
import com.mubashshir.lokal.ui.screens.PlaylistsScreen
import com.mubashshir.lokal.ui.screens.SettingsScreen

@Composable
fun LokalNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
        composable(Screen.Playlists.route) { PlaylistsScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
        composable(Screen.Player.route) { PlayerScreen() }
    }
}
