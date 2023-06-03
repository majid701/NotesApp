package com.majidabdul.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.majidabdul.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        content = { padding ->
                            NavHost(navController = navController, startDestination = "list") {
                                composable("list") {
                                    MainScreen(viewModel = MainViewModel(), padding)
                                }
                                composable("addnote") {
                                    AddNoteScreen()
                                }
                            }
                        },
                        floatingActionButton = {
                            AddNoteFloatingButton(addNoteClicked = { navController.navigate("addnote") })
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun AddNoteFloatingButton(addNoteClicked: () -> Unit) {
    FloatingActionButton(
        onClick = addNoteClicked,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "ADD NOTE"
        )
    }
}


@Composable
fun NoteCard(title: String) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}


@Composable
fun AddNoteScreen() {
    Text(text = "TODO Create note screen")
}

@Composable
fun MainScreen(viewModel: MainViewModel, padding: PaddingValues) {
    val notes by viewModel.getNotes().collectAsState(initial = emptyList())

    LazyColumn {
        items(notes) { note ->
            NoteCard(title = note.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard(title = "This is a long note title")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NotesAppTheme {
        // MainScreen(viewModel = MainViewModel())
    }
}