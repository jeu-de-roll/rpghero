package com.example.rpghero.mainMenu

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.rpghero.mainMenu.NamedTextField
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.json.JSONObject
import java.util.prefs.Preferences


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ParamsMenu(navigateToHomeScreen: () -> Unit, connected: Boolean)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ){
        Text(
            "PARAMETERS",
            style = MaterialTheme.typography.bodyLarge
        )
        SoundParam()
        VibrationParam()
        if(connected)
        {
            ConnectedParams(navigateToHomeScreen = navigateToHomeScreen)
        }
        else
        {
            DisconnectedParams(navigateToHomeScreen = navigateToHomeScreen)
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
            style = MaterialTheme.typography.bodySmall
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            modifier = Modifier.weight(1f)
        )
        Text(
            text = sliderPosition.toString().slice(arrayListOf(0, 1, 2)),
            style = MaterialTheme.typography.bodySmall,
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
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(end = 8.dp))
        Switch(
            checked = vibration,
            onCheckedChange = { vibration = it })
    }
}

@Composable
fun ConnectedParams(navigateToHomeScreen: () -> Unit) {
    Divider(modifier = Modifier.padding(bottom = 8.dp))
    Column {
        Text(
            "Username : bipboup KakouKakou",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            "Email : adresse@gmail.com",
            style = MaterialTheme.typography.bodySmall
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Button(onClick = { navigateToHomeScreen() }) {
                Text(
                    "Disconnection",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxSize().weight(1f))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navigateToHomeScreen() }) {
                Text("Leave Session", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DisconnectedParams(navigateToHomeScreen: () -> Unit)
{
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Divider(modifier = Modifier.padding(bottom = 8.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp))
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val created = CreateOrGetUser(email, password)
                if (created)
                    navigateToHomeScreen()
            }
        ) {
            Text(
                "Connection",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(
                "Register",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun CreateOrGetUser(email: String, password: String): Boolean {
    var created = false

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.contentType(ContentType.Application.Json)
        val json = JSONObject()
            .put("email", email)
            .put("password", password)
            .put("username", email.split("@")[0])
            .toString()

        request.setBody(json)
        request.url("http://10.0.2.2:3000/api/users/")

        val response: HttpResponse =
            client.post(request)

        if (response.status == HttpStatusCode.Created)
            created = true
    }

    return created
}