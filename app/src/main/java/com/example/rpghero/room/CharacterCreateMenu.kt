package com.example.rpghero.room

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rpghero.mainMenu.CreateRoom
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.formData
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
import org.json.JSONObject
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CreateCharacterMenu(navigateToCharacterScreen: () -> Unit)
{
    val context = LocalContext.current
    var name by rememberSaveable { mutableStateOf("") }
    val characterType = arrayOf("D&D 5e", "Pathfinder", "Vampire: TM")
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedText by rememberSaveable { mutableStateOf(characterType[0]) }

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    characterType.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val created = CreateCharacter(context, name, selectedText)
                if (created)
                    navigateToCharacterScreen()
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
fun CreateCharacter(context: Context, name: String, characterType: String): Boolean {
    var created = false

    var characterName: String

    if (name.isNullOrEmpty() or characterType.isNullOrEmpty())
        return created

    val am: AssetManager = context.applicationContext.assets

    val file: File = createTempFile()

    when (characterType) {
        "D&D 5e" -> am.open("dnd_5e.pdf").copyTo(file.outputStream())
        "Pathfinder" -> am.open("Pathfinder_en.pdf").copyTo(file.outputStream())
        "Vampire: TM" -> am.open("Vampire_5epdf.pdf").copyTo(file.outputStream())
    }

    val sharedPref = context.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

    runBlocking {
        val client = HttpClient(CIO)

        val response: HttpResponse =
            client.post("http://10.0.2.2:3000/api/characters/" + sharedPref.getString("gameId", "")!!)
            {
                contentType(ContentType.Application.Json)
                setBody(
                    JSONObject()
                    .put("name", name)
                    .put("game", sharedPref.getString("gameId", "")!!)
                    .put("file", file.readBytes())
                    .put("type", characterType)
                    .toString()
                )
            }

        created = true

        characterName = JSONObject(response.bodyAsText()).get("name").toString()

        created = true
    }

    with (sharedPref.edit()) {
        putString("characterName", characterName)
        apply()
    }

    return created
}