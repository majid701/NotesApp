package com.majidabdul.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.firestore.FirebaseFirestore
import com.majidabdul.notesapp.domain.repository.NotesRepositoryImpl
import com.majidabdul.notesapp.screens.edit_note.AddNoteScreen
import com.majidabdul.notesapp.screens.edit_note.EditNoteViewModel
import com.majidabdul.notesapp.screens.notes_list.NotesScreen
import com.majidabdul.notesapp.screens.notes_list.NotesViewModel
import com.majidabdul.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var editNoteViewModel: EditNoteViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         * This can be refactored with dependency injection using hilt
         * Leaving out for now because of time constraints
         */
        val notesRepository = NotesRepositoryImpl(FirebaseFirestore.getInstance())
        notesViewModel = NotesViewModel(notesRepository)
        editNoteViewModel = EditNoteViewModel(notesRepository)

        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    /*
                     * Navigation routes should be refactored and not used as hardcoded strings
                     */
                    NavHost(navController = navController, startDestination = "notes_list") {
                        composable("notes_list") {
                            NotesScreen(
                                viewModel = notesViewModel,
                                openEditNote = {
                                    navController.navigate("edit_note_screen?noteId=${it?.id}")
                                }
                            )
                        }
                        composable(
                            "edit_note_screen?noteId={noteId}",
                            arguments = listOf(navArgument("noteId") { defaultValue = "-1" })
                        ) {
                            val noteId = it.arguments?.getString("noteId")
                            AddNoteScreen(
                                noteId = noteId,
                                viewModel = editNoteViewModel,
                                popUp = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NotesAppTheme {
        // MainScreen(viewModel = MainViewModel())
        //vxAddNoteScreen()
    }
}