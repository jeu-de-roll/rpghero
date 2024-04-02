package com.example.rpghero.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NamedTextField (name : String, visualTransformation: VisualTransformation)
{
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            name,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = text,
            onValueChange = { text = it },
            visualTransformation = visualTransformation
        )

    }
}

@Composable
fun JoinMenu(navigateToPlayerSelectionScreen: () -> Unit)
{
    Column (
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ){
        NamedTextField(name = "Name", VisualTransformation.None)
        Spacer(modifier = Modifier.height(16.dp))
        NamedTextField(name = "Password", PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                navigateToPlayerSelectionScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "JOIN",
                fontSize = 36.sp
            )
        }
    }
}

@Composable
fun CreateMenu()
{
    Column (
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ){
        NamedTextField(name = "Name", VisualTransformation.None)
        Spacer(modifier = Modifier.height(16.dp))
        NamedTextField(name = "Password", PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "CREATE",
                fontSize = 36.sp
            )
        }
    }
}

@Composable
fun PlayerMenu(existingPlayers : Array<String>)
{
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp),
        verticalArrangement = Arrangement.Center
    ){
        NamedTextField(name = "Pseudo", VisualTransformation.None)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "JOIN AS NEW PLAYER",
                fontSize = 26.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Choose an existing player",
            fontSize = 15.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        LazyColumn (
            modifier = Modifier
                .padding(horizontal = 48.dp)
        ){
            items(existingPlayers) { player ->
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = player,
                    modifier = Modifier.clickable {

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewJoin() {
    //JoinMenu()
}

@Preview
@Composable
fun PreviewCreate() {
    CreateMenu()
}

@Preview
@Composable
fun PreviewPlayer() {
    val testPlayers = arrayOf(
        "Existing player 1",
        "Existing player 2",
        "Existing player 3",
        "Existing player 4",
        "Existing player 5",
        "Existing player 6",
        "Existing player 7",
        "Existing player 8",
        "Existing player 9",
        "Existing player 10",
    )

    PlayerMenu(existingPlayers = testPlayers)
}