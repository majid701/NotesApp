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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majidabdul.notesapp.R
import com.majidabdul.notesapp.composable.BasicField
import com.majidabdul.notesapp.composable.BasicFieldMulti
import com.majidabdul.notesapp.extensions.fieldModifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    noteId: String?,
    viewModel: EditNoteViewModel,
    popUp: () -> Unit
) {
    val note by viewModel.note

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
            text = stringResource(R.string.title), value = note.title,
            viewModel::onTitleChange, Modifier.fieldModifier()
        )
        BasicFieldMulti(
            text = stringResource(R.string.body_hint), value = note.body,
            viewModel::onBodyChange, Modifier.fieldModifier()
        )
        Button(onClick = { viewModel.onSaveClicked(popUp) }, modifier = Modifier.fillMaxWidth(1f)) {
            Text(text = stringResource(R.string.save_button))
        }
    }
}

@Preview
@Composable
fun AddNoteScreenPreview() {
    // AddNoteScreen(popUp = {}, noteId = "-1")
}