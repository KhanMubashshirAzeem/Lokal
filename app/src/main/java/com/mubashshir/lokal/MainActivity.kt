package com.mubashshir.lokal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mubashshir.lokal.ui.screens.MainScreen
import com.mubashshir.lokal.ui.theme.LokalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokalTheme {
                MainScreen()
            }
        }
    }
}
