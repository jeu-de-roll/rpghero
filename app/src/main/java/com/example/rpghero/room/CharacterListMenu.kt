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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CharacterListMenu {
}

@Composable
fun CharacterButton(characterName : String) {
    Button(
        onClick = {  },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                characterName,
                fontSize = 36.sp
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun CharacterListMenu(characters : Array<String>) {
    LazyColumn (
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp)
    )
    {
        items(characters) { character ->
            Spacer(modifier = Modifier.height(8.dp))
            CharacterButton(characterName = character)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}