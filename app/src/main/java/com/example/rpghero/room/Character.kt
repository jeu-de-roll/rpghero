package com.example.rpghero.room

import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import com.example.rpghero.R
import com.rajat.pdfviewer.compose.PdfRendererViewCompose
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
import java.io.File

@Composable
fun Character () {
    val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)
    val am = LocalContext.current.assets

    val file: File = createTempFile()

    var characterType: String

    runBlocking {
        val client = HttpClient(CIO)
        val request = HttpRequestBuilder()

        request.url("http://10.0.2.2:3000/api/characters/name/" + sharedPref.getString("characterName", "")!!)
        val response: HttpResponse =
            client.get(request)

        characterType = JSONObject(response.bodyAsText()).get("type").toString()
    }

    when (characterType) {
        "D&D 5e" -> am.open("dnd_5e.pdf").copyTo(file.outputStream())
        "Pathfinder" -> am.open("Pathfinder_en.pdf").copyTo(file.outputStream())
        "Vampire: TM" -> am.open("Vampire_5epdf.pdf").copyTo(file.outputStream())
    }

    PdfRendererViewCompose(
        file = file,
        lifecycleOwner = LocalLifecycleOwner.current,
    )
}
