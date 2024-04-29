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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpghero.R
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


@Composable
fun CharacterButton(characterName : String, navigateToSelectedCharacterScreen: () -> Unit) {
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    Button(
        onClick = {
            with (sharedPref.edit()) {
                putString("characterName", characterName)
                apply()
            }
            navigateToSelectedCharacterScreen()
                  },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                characterName,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun CharacterListMenu(navigateToSelectedCharacterScreen: () -> Unit, navigateToCharacterCreationScreen: () -> Unit) {
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    var characters: JSONArray

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.url("http://10.0.2.2:3000/api/characters/all/" + sharedPref.getString("gameId", "")!!) // localhost refer to the Android emulator here. We need the host machine where the web server is, so we use the special IP alias 10.0.2.2 to host loopback interface

        val response: HttpResponse =
            client.get(request)

        characters = JSONArray(response.bodyAsText())
    }

    var characterNames = arrayOf<String>()

    for (i in 0 until characters.length()) {
        val character = characters.getJSONObject(i)
        characterNames += character.getString("name")
    }

    Column() {
        LazyColumn (Modifier.weight(1f)) {
            items(characterNames) { character ->
                Spacer(modifier = Modifier.height(8.dp))
                CharacterButton(characterName = character, navigateToSelectedCharacterScreen)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Button(
            onClick = { navigateToCharacterCreationScreen() },
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