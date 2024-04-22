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
import java.io.File

@Composable
fun Character () {
    val am: AssetManager = LocalContext.current.applicationContext.assets

    val file: File = createTempFile()

    am.open("dnd_5e.pdf").copyTo(file.outputStream())

    PdfRendererViewCompose(
        file = file,
        lifecycleOwner = LocalLifecycleOwner.current,
    )
}
