package com.example.rpghero.room

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChronicsButton(chronic : String, navigateToSelectedChronicScreen: () -> Unit) {
    Button(
        onClick = { navigateToSelectedChronicScreen() },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                chronic,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun ChronicsListMenu(chronics: Array<String>, navigateToSelectedChronicScreen: () -> Unit) {
    LazyColumn (
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp)
    )
    {
        items(chronics) { chronic ->
            Spacer(modifier = Modifier.height(8.dp))
            ChronicsButton(chronic = chronic, navigateToSelectedChronicScreen)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}