package com.majidabdul.notesapp.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

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