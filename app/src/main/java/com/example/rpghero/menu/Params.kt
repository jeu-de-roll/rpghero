package com.example.rpghero.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.rpghero.menu.NamedTextField
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Preview
@Composable
fun ParamsMenu()
{
    var connected by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ){
        Text("PARAMETERS", style = MaterialTheme.typography.headlineLarge)
        SoundParam()
        VibrationParam()
        if(connected)
        {
            ConnectedParams()
        }
        else
        {
            DisconnectedParams()
        }
    }
}

@Composable
fun SoundParam()
{
    var sliderPosition by remember { mutableFloatStateOf(1f) }
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Text(
            "Sound",
            style = MaterialTheme.typography.headlineSmall
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            modifier = Modifier.weight(1f)
        )
        Text(
            text = sliderPosition.toString().slice(arrayListOf(0, 1, 2)),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun VibrationParam()
{
    var vibration by remember { mutableStateOf(true) }
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text("Vibration",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(end = 8.dp))
        Switch(
            checked = vibration,
            onCheckedChange = { vibration = it })
    }
}

@Composable
fun ConnectedParams() {
    Column {
        Divider(modifier = Modifier.padding(bottom = 8.dp))
        Text("Username : bipboup KakouKakou",
            fontSize = 20.sp)
        Text("Email : adresse@gmail.com",
            fontSize = 20.sp)
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(
                    "Disconnection",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun DisconnectedParams()
{
    Divider(modifier = Modifier.padding(bottom = 8.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp))
    {
        NamedTextField(name = "Username or Email", visualTransformation = VisualTransformation.None)
        NamedTextField(name = "Password", visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(
                "Connection",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }

}
