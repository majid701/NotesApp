package com.majidabdul.notesapp.screens.notes_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majidabdul.notesapp.R
import com.majidabdul.notesapp.composable.AddNoteFloatingButton
import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.ui.theme.NotesAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    openEditNote: (Note?) -> Unit
) {
    val notes by viewModel.getNotes().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.list_title),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            })
        },
        floatingActionButton = {
            AddNoteFloatingButton(addNoteClicked = { viewModel.onAddClick(openEditNote) })
        }
    ) {
        /*
         * A searchbar can be added on top here to allow users to search
         * Firestore has limited capabilities on free text search so this can be done client side
         * after fetching the data or in a limited through the database
         */
        LazyColumn(modifier = Modifier.padding(it)) {
            items(notes) { note ->
                NoteCard(
                    title = note.title,
                    onClick = { openEditNote(note) },
                    onDeleteClick = { viewModel.onDeleteClick(note) }
                )
            }
        }
    }
}


@Composable
fun NoteCard(title: String, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
        )
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_note_content_description),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard(
            title = "This is a long note title",
            onClick = {},
            onDeleteClick = {}
        )
    }
}

