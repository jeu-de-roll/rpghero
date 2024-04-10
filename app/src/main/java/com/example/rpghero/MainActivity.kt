package com.example.rpghero

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
                navigateToRoomScreen = { navController.navigate("RoomScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("JoinScreen") {
            JoinScreen(
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToCreateScreen = { navController.navigate("CreateScreen") },
                navigateToParamsScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("CreateScreen") {
            CreateScreen(
                navigateToOpenScreen = { navController.navigate("OpenScreen") },
                navigateToJoinScreen = { navController.navigate("JoinScreen") },
                navigateToParamsScreen = { navController.navigate("ParamScreen") }
            )
        }
        composable("ParamScreen") {
            ParamsScreen(
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
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }
        composable("SessionSettingsScreen") {
            SessionSettingsScreen(
                sessionName = "Baldur's Gate 3",
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("ChronicScreen") {
            ChronicScreen(
                sessionName = "Baldur's Gate 3",
                navigateToCharactersScreen = { navController.navigate("CharacterScreen") },
                navigateToChronicsScreen = { navController.navigate("ChronicsScreen") },
                navigateToFilesScreen = { navController.navigate("FilesScreen") },
                navigateToParamScreen = { navController.navigate("SessionSettingsScreen") }
            )
        }

        composable("CharacterDetailScreen") {
            CharacterDetailScreen(
                sessionName = "Baldur's Gate 3",
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
    Scaffold (
        topBar = {
            TopBarMenu(navigateToOpenScreen, navigateToJoinScreen, navigateToCreateScreen, navigateToParamScreen)
        },
        floatingActionButton = {
            FloatingActionButtonDice()
        },
        content = {
            RollDices()
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoomScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Scaffold (
        topBar = {
            RoomTopMenu(navigateToCharactersScreen, navigateToChronicsScreen, navigateToFilesScreen, navigateToParamScreen)
        },
        floatingActionButton = {
            FloatingActionButtonDice()
        },
        content = {
            Surface(modifier = Modifier
                .padding(top=124.dp)
                .fillMaxWidth()) {
                Text(
                    text = sessionName,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
            RollDices()
        }
    )
}

@Composable
fun OpenScreen(navigateToRoomScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column() {
        TopBarMenu(
            navigateToOpenScreen = { },
            navigateToJoinScreen = navigateToJoinScreen,
            navigateToCreateScreen = navigateToCreateScreen,
            navigateToParamScreen = navigateToParamScreen,
        )
        SessionListMenu(
            sessions = arrayOf(
                "Baldur's Gate 3",
                ),
            navigateToRoomScreen = navigateToRoomScreen,
        )
    }
}

@Composable
fun JoinScreen(navigateToOpenScreen: () -> Unit, navigateToCreateScreen: () -> Unit, navigateToParamsScreen: () -> Unit) {
    Column() {
        TopBarMenu(
            navigateToOpenScreen = navigateToOpenScreen,
            navigateToJoinScreen = { },
            navigateToCreateScreen = navigateToCreateScreen,
            navigateToParamScreen = navigateToParamsScreen,
        )
        JoinMenu()
    }
}

@Composable
fun CreateScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToParamsScreen: () -> Unit) {
    Column() {
        TopBarMenu(
            navigateToOpenScreen = navigateToOpenScreen,
            navigateToJoinScreen = navigateToJoinScreen,
            navigateToCreateScreen = { },
            navigateToParamScreen = navigateToParamsScreen,
        )
        CreateMenu()
    }
}

@Composable
fun ParamsScreen(navigateToOpenScreen: () -> Unit, navigateToJoinScreen: () -> Unit, navigateToCreateScreen: () -> Unit) {
    Column() {
        TopBarMenu(
            navigateToOpenScreen = navigateToOpenScreen,
            navigateToJoinScreen = navigateToJoinScreen,
            navigateToCreateScreen = navigateToCreateScreen,
            navigateToParamScreen = { }
        )
        ParamsMenu()
    }
}

@Composable
fun CharacterScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedCharacterScreen: () -> Unit) {
    Column() {
        RoomTopMenu(
            { },
            navigateToChronicsScreen,
            navigateToFilesScreen,
            navigateToParamScreen
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
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

@Composable
fun ChronicsScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit, navigateToSelectedChronicScreen: () -> Unit) {
    Column() {
        RoomTopMenu(
            navigateToCharactersScreen,
            { },
            navigateToFilesScreen,
            navigateToParamScreen
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
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

@Composable
fun FilesScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column() {
        RoomTopMenu(
            navigateToCharactersScreen,
            navigateToChronicsScreen,
            { },
            navigateToParamScreen
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        ShareFilePage()
    }
}

@Composable
fun SessionSettingsScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column() {
        RoomTopMenu(
            navigateToCharactersScreen,
            navigateToChronicsScreen,
            navigateToFilesScreen,
            { },
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        SessionSettingsMenu()
    }
}

@Composable
fun ChronicScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column {
        RoomTopMenu(
            navigateToCharactersScreen,
            navigateToChronicsScreen,
            navigateToFilesScreen,
            navigateToParamScreen
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Chronics()
    }
}

@Composable
fun CharacterDetailScreen(sessionName: String, navigateToCharactersScreen: () -> Unit, navigateToChronicsScreen: () -> Unit, navigateToFilesScreen: () -> Unit, navigateToParamScreen: () -> Unit) {
    Column {
        RoomTopMenu(
            navigateToCharactersScreen,
            navigateToChronicsScreen,
            navigateToFilesScreen,
            navigateToParamScreen
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = sessionName,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Character()
    }
}

@Composable
fun FloatingActionButtonDice() {
    FloatingActionButton(
        onClick = { },
        modifier = Modifier
    ) {
        Icon(painter = painterResource(id = R.drawable.dice), "Dice")
    }
}

@Composable
fun RollDices()
{
    var clicked by remember { mutableStateOf(false) }
    var dice by remember { mutableIntStateOf(404) }
    val rand = Random
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable
        {
            dice = rand.nextInt(20) + 1
            clicked = true
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (clicked) {
            Text(text = dice.toString(), style = MaterialTheme.typography.displayLarge)
        }
        else {
            Text("Tap on Screen to roll for initiative",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center)
        }
    }
}