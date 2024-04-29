package com.example.rpghero.mainMenu

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.HttpClient
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
import org.json.JSONArray
import org.json.JSONObject

@Composable
fun SessionButton(sessionName : String, navigateToRoomScreen: () -> Unit, sessions: JSONArray) {
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    var gameId = ""

    for (i in 0 until sessions.length()) {
        val session = sessions.getJSONObject(i)
        if (session.get("name") == sessionName)
            gameId = session.get("_id").toString()
    }

    Button(
        onClick = {
            with (sharedPref.edit()) {
                putString("gameName", sessionName)
                putString("gameId", gameId)
                apply()
            }
            navigateToRoomScreen()
                  },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                sessionName,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun SessionListMenu(navigateToRoomScreen: () -> Unit) {
    var sessions: JSONArray

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.url("http://10.0.2.2:3000/api/games/") // localhost refer to the Android emulator here. We need the host machine where the web server is, so we use the special IP alias 10.0.2.2 to host loopback interface

        val response: HttpResponse =
            client.get(request)

        sessions = JSONArray(response.bodyAsText())
    }

    var sessionNames = arrayOf<String>()

    for (i in 0 until sessions.length()) {
        val session = sessions.getJSONObject(i)
        sessionNames += session.getString("name")
    }

    LazyColumn (
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp)
    )
    {
        items(sessionNames) { session ->
            Spacer(modifier = Modifier.height(8.dp))
            SessionButton(sessionName = session, navigateToRoomScreen = navigateToRoomScreen, sessions = sessions)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

