package com.majidabdul.notesapp.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicField(
    text: String,
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicFieldMulti(
    text: String,
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text) }
    )
}