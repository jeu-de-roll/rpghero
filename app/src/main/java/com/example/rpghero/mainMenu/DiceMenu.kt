package com.example.rpghero.mainMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun DiceMenu(
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val padding = screenWidth / 4

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.5.dp),
        horizontalArrangement = Arrangement.End,
    )
    {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Gray,
                    RoundedCornerShape(25.dp)
                )
                .clip(RoundedCornerShape(25.dp))
        ) {
            Icon(
                Icons.Rounded.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier
                    .width(3 * padding / 4)
            )
        }
    }
}