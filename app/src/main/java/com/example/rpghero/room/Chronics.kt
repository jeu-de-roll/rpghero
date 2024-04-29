package com.example.rpghero.room

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Updater
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.put
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
fun Chronics () {
    val context = LocalContext.current
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    var game: JSONObject
    var chronic: String

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.url(
            "http://10.0.2.2:3000/api/games/" + sharedPref.getString(
                "gameId",
                ""
            )!!
        ) // localhost refer to the Android emulator here. We need the host machine where the web server is, so we use the special IP alias 10.0.2.2 to host loopback interface

        val response: HttpResponse =
            client.get(request)

        game = JSONObject(response.bodyAsText())
        chronic = game.getJSONArray("chronics").get(sharedPref.getInt("chronicIndex", 0)).toString()
    }

    var text by remember { mutableStateOf(chronic) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        value = text,
        onValueChange = {
            text = it
            UpdateChronic(context, text, game, sharedPref.getInt("chronicIndex", 0))
        }
    )
}

fun UpdateChronic(context: Context, text: String, game: JSONObject, chronicIndex: Int) {
    val sharedPref = context.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)
    game.getJSONArray("chronics").put(chronicIndex, text)

    runBlocking {
        val client = HttpClient(CIO)

        val response: HttpResponse =
            client.put("http://10.0.2.2:3000/api/games/" + sharedPref.getString("gameId", "")!!)
            {
                contentType(ContentType.Application.Json)
                setBody(
                    game.toString()
                )
            }

    }
}