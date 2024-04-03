package com.example.rpghero.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.rpghero.R

@Composable
fun Character () {
    Image(
        painter = painterResource(id = R.drawable.character_sheet_dnd),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}