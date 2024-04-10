package com.example.rpghero

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
import com.example.rpghero.room.RoomTopMenu
import com.example.rpghero.room.SessionSettingsMenu
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RPGHeroTheme {
                MainNavigation()
            }
        }
    }
}

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
                navigateToParamsScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("CreateScreen") {
            CreateScreen(
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToParamsScreen = { navController.navigate("ParamScreen") }
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
            RoomScreen(
                sessionName = "Baldur's Gate 3",
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterScreen") {
            CharacterScreen(sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") },
                navigateToSelectedCharacterScreen = { navController.navigate("CharacterDetailScreen") }
            )
        }
        composable("ChronicsScreen") {
            ChronicsScreen(
                sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") },
                navigateToSelectedChronicScreen = { navController.navigate("ChronicScreen") },
            )
        }
        composable("FilesScreen") {
            FilesScreen(
                sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }
        composable("SessionSettingsScreen") {
            SessionSettingsScreen(
                sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("ChronicScreen") {
            ChronicScreen(
                sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterDetailScreen") {
            CharacterDetailScreen(
                sessionName = "Baldur's Gate 3",
                navigateToHomeScreen = { navController.navigate("RoomScreen") },
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamScreen: () -> Unit) {

    var RollsAmount = remember { mutableStateListOf<Int>(2, 4, 6, 8, 10, 12, 20, 100, 404) }
    var DiceIndex by remember { mutableIntStateOf(6) }

    Scaffold (
        topBar = {
            TopBarMenu(navigateToOpenScreen, navigateToJoinScreen, navigateToCreateScreen, navigateToParamScreen)
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
            RollDices(RollsAmount[DiceIndex])
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth()) {
                SessionListMenu(
                    sessions = arrayOf(
                        "Baldur's Gate 3",
                    ),
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateScreen(navigateToHomeScreen: () -> Unit, navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToParamsScreen: () -> Unit) {
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
        },
        content = {
            Surface(modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth())
            {
                CreateMenu()
            }
        }
    )
}

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
            FloatingActionButtonHome(
                {
                    navigateToDiceScreen()
                }
            )
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
fun CharacterScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedCharacterScreen: () -> Unit) {
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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
                CharacterListMenu(
                    characters = arrayOf(
                        "Tav",
                        "Astarion",
                        "Lae'zel",
                        "Shadowheart",
                    ),
                    navigateToSelectedCharacterScreen
                )
            }
        }
    )
}

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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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
                ChronicsListMenu(
                    chronics = arrayOf(
                        "Act 1",
                        "Act 2",
                        "Act 3",
                    ),
                    navigateToSelectedChronicScreen
                )
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SessionSettingsScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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
fun CharacterDetailScreen(sessionName: String, navigateToHomeScreen: () -> Unit, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
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
            FloatingActionButtonHome(
                {
                    navigateToHomeScreen()
                }
            )
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
                Character()
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
            Text("D" + RollMaxAmount.toString(), color = Color.Red, style = MaterialTheme.typography.bodyMedium)
            Text(text = RollAmount.toString(), style = MaterialTheme.typography.bodyLarge)
        }
        else {
            Text("Tap on Screen to roll for initiative",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)
        }
    }
}