package com.mubashshir.lokal.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val outlinedIcon: ImageVector? = null,
    val filledIcon: ImageVector? = null
)
{
    data object Home : Screen(
        "home",
        "Home",
        Icons.Outlined.Home,
        Icons.Filled.Home
    )

    data object Favorites : Screen(
        "favorites",
        "Favorites",
        Icons.Outlined.FavoriteBorder,
        Icons.Filled.Favorite
    )

    data object Playlists : Screen(
        "playlists",
        "Playlists",
        Icons.Outlined.PlayArrow,
        Icons.Filled.PlayArrow
    )

    data object Settings : Screen(
        "settings",
        "Settings",
        Icons.Outlined.Settings,
        Icons.Filled.Settings
    )

    data object Player :
        Screen("player", "Player")

    companion object
    {
        val bottomNavItems = listOf(
            Home,
            Favorites,
            Playlists,
            Settings
        )
    }
}
