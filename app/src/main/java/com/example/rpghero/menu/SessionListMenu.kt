package com.example.rpghero.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SessionButton(sessionName : String) {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                sessionName,
                fontSize = 36.sp
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun SessionListMenu(sessions : Array<String>) {
    LazyColumn (
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp)
    )
    {
        items(sessions) { session ->
            Spacer(modifier = Modifier.height(8.dp))
            SessionButton(sessionName = session)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewSessionList() {
    val sessions = arrayOf(
        "Chronique 1",
        "Chronique 2",
        "Chronique 3",
        "Chronique 4",
        "Chronique 5",
        "Chronique 6",
        "Chronique 7",
        "Chronique 8",
        "Chronique 9",
        "Chronique 10",
        "Chronique 11",
        "Chronique 12",
        "Chronique 13",
        "Chronique 14"
    )
    SessionListMenu(sessions = sessions)
}
