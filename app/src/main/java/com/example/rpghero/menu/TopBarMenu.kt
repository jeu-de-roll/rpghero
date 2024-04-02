package com.example.rpghero.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.compose.rememberNavController

@Composable
fun TopBarMenu(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    val navController = rememberNavController()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {
        OpenBox(modifier = Modifier.size(buttonWidth), navigateToOpenScreen = navigateToOpenScreen)
        JoinBox(modifier = Modifier.size(buttonWidth), navigateToJoinScreen = navigateToJoinScreen)
        CreateBox(modifier = Modifier.size(buttonWidth), navigateToCreateScreen = navigateToCreateScreen)
        SettingsBox(modifier = Modifier.size(buttonWidth))
    }
}

@Composable
private fun OpenBox(modifier: Modifier, navigateToOpenScreen: () -> Unit)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToOpenScreen();
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "OPEN",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun JoinBox(modifier: Modifier, navigateToJoinScreen: () -> Unit)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToJoinScreen()
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "JOIN",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun CreateBox(modifier: Modifier, navigateToCreateScreen: () -> Unit)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToCreateScreen()
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "CREATE",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun SettingsBox(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black)
            .clickable {

            }
    ) {
        Icon(
            Icons.Rounded.Settings,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * buttonWidth / 3),
        )
    }
}
