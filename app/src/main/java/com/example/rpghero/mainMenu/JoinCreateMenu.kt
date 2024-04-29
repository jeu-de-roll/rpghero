package com.example.rpghero.mainMenu

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.util.JsonWriter
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import kotlin.concurrent.thread

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
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = text,
            onValueChange = { text = it },
            visualTransformation = visualTransformation,
            textStyle = MaterialTheme.typography.bodySmall
        )

    }
}

@Composable
fun JoinMenu()
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

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "JOIN",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CreateMenu(navigateToRoomScreen: () -> Unit)
{
    val context = LocalContext.current

    var description by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = description,
            onValueChange = { description = it },
            label = { Text("Enter description") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                var created = CreateRoom(context, name, description)
                if (created)
                    navigateToRoomScreen()
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "CREATE",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun CreateRoom(context: Context, name: String, description: String): Boolean {
    var response: HttpResponse

    var gameId = ""
    var gameName = ""

    var created = false

    if (name.isNullOrEmpty() or description.isNullOrEmpty())
        return created

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.contentType(ContentType.Application.Json)
        val json = JSONObject()
            .put("name", name)
            .put("description", description)
            .put("master", "6627c7f51f020a760685a6ac")
            .put("player", "[]")
            .put("blankSheet", "")
            .toString()

        request.setBody(json)
        request.url("http://10.0.2.2:3000/api/games/")

        response = client.post(request)

        gameId = JSONObject(response.bodyAsText()).get("_id").toString()
        gameName = JSONObject(response.bodyAsText()).get("name").toString()

        created = true
    }

    val sharedPref = context.getSharedPreferences("currentRoom", MODE_PRIVATE)

    with (sharedPref.edit()) {
        putString("gameName", gameName)
        putString("gameId", gameId)
        apply()
    }

    return created
}