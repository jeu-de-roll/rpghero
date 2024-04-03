package com.example.rpghero.mainMenu

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
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Preview
@Composable
fun ShareFilePage()
{
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp))
    {
        //BAR
        Text("YOUR FILES", fontSize = 34.sp)
        Row (modifier = Modifier
            .weight(1f)
            .padding(vertical = 16.dp)){
            FilesGrid(arrayOf("Image 1", "Ma carte.pdf", "Pnj artwork", "Mp3 404", "Meme trop marrant", "Machin", "Chose", "Machin", "Chose", "Machin", "Chose", "Machin", "Chose", "Machin", "Chose"))
            //Slider(value = , onValueChange = )
        }
        Button(onClick = { /*TODO*/ })
        {
            Text("Import File", fontSize = 34.sp)
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
fun File (name : String)
{
    val isPopUpOpen = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.placeholder),
            contentDescription = null)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = name, fontSize = 16.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            IconButton(
                onClick =
                {
                    isPopUpOpen.value = !isPopUpOpen.value
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = null
                )
            }
        }
    }
    if (isPopUpOpen.value)
    {
        SharePopup(onClose = { isPopUpOpen.value = false })
    }
}

//@Preview
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
            Text("Share to a player", fontSize = 14.sp)
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
            Text("Share to all players", fontSize = 14.sp)
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
            Text("Delete", fontSize = 14.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}