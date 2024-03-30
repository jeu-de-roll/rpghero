package com.example.rpghero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import com.example.rpghero.ui.theme.RPGHeroTheme
import com.example.rpghero.menu.TopBarMenu
import com.example.rpghero.menu.DiceMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RPGHeroTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    Column {
        TopBarMenu()
        DiceMenu()
    }
}

