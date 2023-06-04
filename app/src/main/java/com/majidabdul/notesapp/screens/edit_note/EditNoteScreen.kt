package com.majidabdul.notesapp.screens.edit_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.majidabdul.notesapp.composable.BasicField
import com.majidabdul.notesapp.composable.BasicFieldMulti
import com.majidabdul.notesapp.extensions.fieldModifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    viewModel: EditNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val note = viewModel.note

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicField(text = "Title", value = note.value.title, viewModel::onTitleChange, Modifier.fieldModifier())
        BasicFieldMulti(text = "What's on your mind today", value = note.value.body, viewModel::onBodyChange, Modifier.fieldModifier())
    }
}