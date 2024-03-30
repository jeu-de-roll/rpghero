package com.example.rpghero.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Preview
@Composable
fun TopBarMenu(
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {
        OpenBox(modifier = Modifier.size(buttonWidth))
        JoinBox(modifier = Modifier.size(buttonWidth))
        CreateBox(modifier = Modifier.size(buttonWidth))
        SettingsBox(modifier = Modifier.size(buttonWidth))
    }
}

@Composable
private fun OpenBox(modifier: Modifier)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black),
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
private fun JoinBox(modifier: Modifier)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black),
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
private fun CreateBox(modifier: Modifier)
{
    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black),
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
private fun SettingsBox(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 4

    Box(
        modifier = modifier
            .background(color = Color(0xFFBDBDBD))
            .border(width = 1.dp, color = Color.Black),
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
