package com.example.rpghero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rpghero.ui.theme.RPGHeroTheme
import com.example.rpghero.menu.TopBarMenu
import com.example.rpghero.menu.DiceMenu
import com.example.rpghero.menu.JoinMenu
import com.example.rpghero.menu.CreateMenu
import com.example.rpghero.menu.SessionListMenu
import com.example.rpghero.menu.ParamsMenu

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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamScreen = { navController.navigate("ParamScreen") })
        }
        composable("OpenScreen") {
            OpenScreen()
        }
        composable("JoinScreen") {
            JoinScreen()
        }
        composable("CreateScreen") {
            CreateScreen()
        }
        composable("ParamScreen") {
            ParamsScreen(
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") }
            )
        }
    }
}

@Composable
fun HomeScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column {
        TopBarMenu(navigateToOpenScreen, navigateToJoinScreen, navigateToCreateScreen, navigateToParamScreen)
        DiceMenu()
    }
}

@Composable
fun OpenScreen() {
    SessionListMenu(sessions = arrayOf(
        "Test session 1",
        "Test session 2",
        "Test session 3",
        ))
}

@Composable
fun JoinScreen() {
    JoinMenu()
}

@Composable
fun CreateScreen() {
    CreateMenu()
}

@Composable
fun ParamsScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit)
{
    Column() {
        TopBarMenu(
            navigateToOpenScreen = navigateToOpenScreen,
            navigateToJoinScreen = navigateToJoinScreen,
            navigateToCreateScreen = navigateToCreateScreen,
            navigateToParamScreen = { /*Nothing here, we dont want to travel to the params form the params*/ }
        )
        ParamsMenu()
    }
}