package com.example.rpghero.room

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Chronics () {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it }
    )
}