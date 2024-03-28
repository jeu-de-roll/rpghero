package com.example.rpghero

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.painterResource
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rpghero.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement.Horizontal
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person4
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}


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
                onClick = { /*TODO*/ },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    Icons.Filled.MoreHoriz,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun sharePopup()
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
