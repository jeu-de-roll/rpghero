package com.example.rpghero.room

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import com.example.rpghero.R
import java.io.File

@Preview
@Composable
fun ShareFilePage()
{
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        result.value = it
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    {
        //BAR
        Text(
            "YOUR FILES",
            style = MaterialTheme.typography.bodyMedium
        )
        Row (
            modifier = Modifier
            .weight(1f)
            .padding(vertical = 16.dp)){
            FilesGrid(arrayOf())
        }
        Button(onClick = { launcher.launch(arrayOf("application/pdf")) })
        {
            Text(
                "Import File",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FilesGrid (files : Array<String>)
{
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(files)
        {
                file -> File(file)
        }
    }
}

@Composable
fun SharePopup(onClose: ()->Unit)
{
    Column (
        modifier = Modifier
            .width(158.dp)
            .background(Color.Gray)
            .border(BorderStroke(1.dp, Color.DarkGray))
    ){
        TextButton (
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),

            ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                "Share to a player",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        TextButton (
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
        ) {
            Icon(
                Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                "Share to all players",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        TextButton (
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                "Delete",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}