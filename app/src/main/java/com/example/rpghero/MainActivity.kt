package com.example.rpghero

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.activity
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rpghero.ui.theme.RPGHeroTheme

import com.example.rpghero.mainMenu.TopBarMenu
import com.example.rpghero.mainMenu.JoinMenu
import com.example.rpghero.mainMenu.CreateMenu
import com.example.rpghero.mainMenu.SessionListMenu
import com.example.rpghero.mainMenu.ParamsMenu
import com.example.rpghero.room.Character

import com.example.rpghero.room.ShareFilePage
import com.example.rpghero.room.CharacterListMenu
import com.example.rpghero.room.Chronics
import com.example.rpghero.room.ChronicsListMenu
import com.example.rpghero.room.CreateCharacterMenu
import com.example.rpghero.room.RoomTopMenu
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applicationContext

        setContent {
            RPGHeroTheme {
                MainNavigation()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("OpenScreen") {
            OpenScreen(
                navigateToHomeScreen = { navController.navigate("HomeScreen") },
                navigateToRoomScreen = { navController.navigate("RoomScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("JoinScreen") {
            JoinScreen(
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamsScreen = { navController.navigate("ParamScreen") },
            )
        }
        composable("CreateScreen") {
            CreateScreen(
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToParamsScreen = { navController.navigate("ParamScreen") },
                navigateToRoomScreen = { navController.navigate("RoomScreen") }
            )
        }
        composable("ParamScreen") {
            ParamsScreen(
                navigateToHomeScreen = { navController.navigate("HomeScreen")},
                navigateToDiceScreen = { navController.navigate("HomeScreen") },
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") }
            )
        }

        composable("RoomScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            RoomScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            CharacterScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") },
                navigateToSelectedCharacterScreen = { navController.navigate("CharacterDetailScreen") },
                navigateToCharacterCreationScreen = { navController.navigate("CharacterCreationScreen") }
            )
        }
        composable("ChronicsScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            ChronicsScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") },
                navigateToSelectedChronicScreen = { navController.navigate("ChronicScreen") },
            )
        }
        composable("FilesScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            FilesScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }
        composable("SessionSettingsScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            SessionSettingsScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("HomeScreen") },
                navigateToRoomScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("ChronicScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            ChronicScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterDetailScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            CharacterDetailScreen(
                character = sharedPref.getString("characterName", "")!!,
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterCreationScreen") {
            val sharedPref = LocalContext.current.getSharedPreferences("currentRoom", Context.MODE_PRIVATE)

            CharacterCreationScreen(
                sessionName = sharedPref.getString("gameName", "")!!,
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") },
                navigateToSelectedCharacterScreen = { navController.navigate("CharacterDetailScreen") }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamScreen: () -> Unit) {

    val rollsAmount = remember { mutableStateListOf(2, 4, 6, 8, 10, 12, 20, 100, 404) }
    var diceIndex by remember { mutableIntStateOf(6) }

    Scaffold (
        topBar = {
            TopBarMenu(navigateToOpenScreen, navigateToJoinScreen, navigateToCreateScreen, navigateToParamScreen)
        },
        floatingActionButton = {
            FloatingActionButtonDice {
                diceIndex++
                diceIndex %= rollsAmount.size
            }
        },
        content = {
            RollDices(rollsAmount[diceIndex])
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoomScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {

    var RollsAmount = remember { mutableStateListOf<Int>(2, 4, 6, 8, 10, 12, 20, 100, 404) }
    var DiceIndex by remember { mutableIntStateOf(6) }

    Scaffold (
        topBar = {
            RoomTopMenu(navigateToCharactersScreen, navigateToChronicsScreen, navigateToFilesScreen, navigateToParamScreen)
        },
        floatingActionButton = {
            FloatingActionButtonDice(
                {
                    DiceIndex++
                    DiceIndex = DiceIndex % RollsAmount.size
                }
            )
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth()) {
                Text(
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }
            RollDices(RollsAmount[DiceIndex])
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OpenScreen(navigateToHomeScreen: () -> Unit, navigateToRoomScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            TopBarMenu(
                navigateToOpenScreen = { },
                navigateToJoinScreen = navigateToJoinScreen,
                navigateToCreateScreen = navigateToCreateScreen,
                navigateToParamScreen = navigateToParamScreen,
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth()) {
                SessionListMenu(
                    navigateToRoomScreen = navigateToRoomScreen,
                )
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JoinScreen(navigateToHomeScreen: () -> Unit, navigateToOpenScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamsScreen: () -> Unit) {
    Scaffold (
        topBar = {
            TopBarMenu(
                navigateToOpenScreen = navigateToOpenScreen,
                navigateToJoinScreen = { },
                navigateToCreateScreen = navigateToCreateScreen,
                navigateToParamScreen = navigateToParamsScreen,
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                JoinMenu()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateScreen(navigateToHomeScreen: () -> Unit, navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToParamsScreen: () -> Unit, navigateToRoomScreen: () -> Unit) {
    Scaffold (
        topBar = {
            TopBarMenu(
                navigateToOpenScreen = navigateToOpenScreen,
                navigateToJoinScreen = navigateToJoinScreen,
                navigateToCreateScreen = { },
                navigateToParamScreen = navigateToParamsScreen,
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                CreateMenu(navigateToRoomScreen)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ParamsScreen(navigateToHomeScreen: () -> Unit, navigateToDiceScreen: () -> Unit, navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit) {
    Scaffold (
        topBar = {
            TopBarMenu(
                navigateToOpenScreen = navigateToOpenScreen,
                navigateToJoinScreen = navigateToJoinScreen,
                navigateToCreateScreen = navigateToCreateScreen,
                navigateToParamScreen = { }
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToDiceScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                ParamsMenu({ navigateToHomeScreen() }, connected = false)
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedCharacterScreen: () -> Unit, navigateToCharacterCreationScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                { },
                navigateToChronicsScreen,
                navigateToFilesScreen,
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                Row (
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 32.dp)
                ) {
                    CharacterListMenu(
                        navigateToSelectedCharacterScreen=navigateToSelectedCharacterScreen,
                        navigateToCharacterCreationScreen=navigateToCharacterCreationScreen
                    )
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChronicsScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedChronicScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                { },
                navigateToFilesScreen,
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                Row (
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 32.dp)
                ) {
                    ChronicsListMenu(
                        navigateToSelectedChronicScreen
                    )
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilesScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                navigateToChronicsScreen,
                { },
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                ShareFilePage()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SessionSettingsScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToRoomScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                navigateToChronicsScreen,
                navigateToFilesScreen,
                { },
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToRoomScreen()
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                ParamsMenu(connected = true, navigateToHomeScreen = navigateToHomeScreen )
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChronicScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                navigateToChronicsScreen,
                navigateToFilesScreen,
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                Chronics()
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(sessionName: String, character: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                navigateToChronicsScreen,
                navigateToFilesScreen,
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "$sessionName - $character",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                Character()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterCreationScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedCharacterScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(
                navigateToCharactersScreen,
                navigateToChronicsScreen,
                navigateToFilesScreen,
                navigateToParamScreen
            )
        },
        floatingActionButton = {
            FloatingActionButtonHome {
                navigateToHomeScreen()
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sessionName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                CreateCharacterMenu(
                    navigateToSelectedCharacterScreen
                )
            }
        }
    )
}

@Composable
fun FloatingActionButtonDice(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier
    ) {
        Column {
            Icon(painter = painterResource(id = R.drawable.dice), "Dice")
        }
    }
}

@Composable
fun FloatingActionButtonHome(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier
    ) {
        Column {
            Icon(
                Icons.Filled.Home,
                contentDescription = null
            )
        }
    }
}

@Composable
fun RollDices(RollMaxAmount: Int)
{
    var clicked by remember { mutableStateOf(false) }
    var RollAmount by remember { mutableIntStateOf(404) }
    val rand = Random
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable
        {
            RollAmount = rand.nextInt(RollMaxAmount) + 1
            clicked = true
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (clicked) {
            Text("d$RollMaxAmount", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
            Text(text = RollAmount.toString(), style = MaterialTheme.typography.bodyLarge)
        }
        else {
            Text("Tap on Screen to roll for initiative",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)
        }
    }
}