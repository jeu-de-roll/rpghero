package com.example.rpghero.room

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun RoomTopMenu(navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToSettingsScreen: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {
        CharacterBox(
            buttonWidth,
            navigateToCharactersScreen = navigateToCharactersScreen,
        )
        ChronicsBox(
            buttonWidth,
            navigateToChronicsScreen = navigateToChronicsScreen)
        FilesBox(
            buttonWidth,
            navigateToFilesScreen = navigateToFilesScreen
        )
        SettingsBox(
            buttonWidth,
            navigateToSettingsScreen = navigateToSettingsScreen
        )
    }
}

@Composable
private fun CharacterBox(width: Dp, navigateToCharactersScreen: () -> Unit)
{
    Box(
        modifier = Modifier
            .background(color = Color(0xFFBDBDBD))
            .size(width)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToCharactersScreen()
            }
    ) {
        Icon(
            Icons.Rounded.Face,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * width / 3),
        )
    }
}

@Composable
private fun ChronicsBox(width: Dp, navigateToChronicsScreen: () -> Unit)
{
    Box(
        modifier = Modifier
            .background(color = Color(0xFFBDBDBD))
            .size(width)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToChronicsScreen()
            }
    ) {
        Icon(
            Icons.Rounded.DateRange,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * width / 3),
        )
    }
}

@Composable
private fun FilesBox(width: Dp, navigateToFilesScreen: () -> Unit)
{
    Box(
        modifier = Modifier
            .background(color = Color(0xFFBDBDBD))
            .size(width)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToFilesScreen()
            }
    ) {
        Icon(
            Icons.Rounded.FolderOpen,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * width / 3),
        )
    }
}

@Composable
private fun SettingsBox(width: Dp, navigateToSettingsScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFFBDBDBD))
            .size(width)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                navigateToSettingsScreen()
            }
    ) {
        Icon(
            Icons.Rounded.Settings,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * width / 3),
        )
    }
}
