package com.example.rpghero.room

import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
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
import java.io.File


@Composable
fun ChronicsButton(index: Int, chronic : String, navigateToSelectedChronicScreen: () -> Unit) {
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    Button(
        onClick = {
            with (sharedPref.edit()) {
                putInt("chronicIndex", index)
                apply()
            }
            navigateToSelectedChronicScreen()
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
                chronic.substring(0, Math.min(chronic.length, 10)) + "...",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ChronicsListMenu(navigateToSelectedChronicScreen: () -> Unit) {
    val context = LocalContext.current

    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    var game: JSONObject
    var chronics: JSONArray

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
        chronics = game.getJSONArray("chronics")
    }

    var chronicsIterable = arrayOf<String>()

    for (i in 0 until chronics.length()) {
        val chronic = chronics.get(i)
        chronicsIterable += chronic.toString()
    }

    Column() {
        LazyColumn (Modifier.weight(1f)) {
            itemsIndexed(chronicsIterable) { index, chronic ->
                Spacer(modifier = Modifier.height(8.dp))
                ChronicsButton(index = index, chronic = chronic, navigateToSelectedChronicScreen)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Button(
            onClick = {
                CreateChronic(context = context, game = game)
                navigateToSelectedChronicScreen()
                      },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Add, "Add", Modifier.size(32.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun CreateChronic(context: Context, game: JSONObject) {
    val sharedPref = context.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    var game = game
    var chronicNum : Int

    game = game.put("chronics", game.getJSONArray("chronics").put(""))

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

        chronicNum = JSONObject(response.bodyAsText()).getJSONArray("chronics").length() - 1
    }

    with (sharedPref.edit()) {
        putInt("chronicIndex", chronicNum)
        apply()
    }
}