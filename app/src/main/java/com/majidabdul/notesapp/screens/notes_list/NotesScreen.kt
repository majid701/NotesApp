package com.majidabdul.notesapp.screens.notes_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majidabdul.notesapp.composable.AddNoteFloatingButton
import com.majidabdul.notesapp.ui.theme.NotesAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    openEditNote: (String?) -> Unit
) {
    val notes by viewModel.getNotes().collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            AddNoteFloatingButton(addNoteClicked = { viewModel.onAddClick(openEditNote) })
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(notes) { note ->
                NoteCard(title = note.title)
            }
        }
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

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard(title = "This is a long note title")
    }
}

