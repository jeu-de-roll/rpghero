package com.example.rpghero.mainMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun TopBarMenu(
    navigateToOpenScreen: () -> Unit,
    navigateToJoinScreen: () -> Unit,
    navigateToCreateScreen: () -> Unit,
    navigateToParamScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {
        TextButtonBox(text = "SESSION" + "\n" + "LIST", onClickEvent = navigateToOpenScreen)
        TextButtonBox(text = "JOIN" + "\n" + "SESSION", onClickEvent = navigateToJoinScreen)
        TextButtonBox(text = "CREATE" + "\n" + "SESSION", onClickEvent = navigateToCreateScreen)
        IconButtonBox(icon = Icons.Rounded.Settings, onClickEvent = navigateToParamScreen)
    }
}

@Composable
fun TextButtonBox(text : String, onClickEvent: () -> Unit)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Box(
        modifier = Modifier
            .background(color = Color(0xFFBDBDBD))
            .size(buttonWidth)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                onClickEvent()
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun IconButtonBox(icon : ImageVector, onClickEvent: () -> Unit)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Box(
        modifier = Modifier
            .size(buttonWidth)
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                onClickEvent();
            }
    ) {
        Icon(
            icon,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(2 * buttonWidth / 3),
        )
    }
}