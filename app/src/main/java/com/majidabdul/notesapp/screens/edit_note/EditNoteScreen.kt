package com.majidabdul.notesapp.screens.edit_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majidabdul.notesapp.composable.BasicField
import com.majidabdul.notesapp.composable.BasicFieldMulti
import com.majidabdul.notesapp.extensions.fieldModifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    noteId: String?,
    viewModel: EditNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    popUp: () -> Unit
) {
    val note = viewModel.note

    LaunchedEffect(Unit) { viewModel.init(noteId) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Note", style = MaterialTheme.typography.titleLarge
        )
        BasicField(
            text = "Title", value = note.value.title,
            viewModel::onTitleChange, Modifier.fieldModifier()
        )
        BasicFieldMulti(
            text = "What's on your mind today", value = note.value.body,
            viewModel::onBodyChange, Modifier.fieldModifier()
        )
        Button(onClick = { viewModel.onSaveClicked(popUp) }, modifier = Modifier.fillMaxWidth(1f)) {
            Text(text = "SAVE")
        }
    }
}

@Preview
@Composable
fun AddNoteScreenPreview() {
    AddNoteScreen(popUp = {}, noteId = "-1")
}