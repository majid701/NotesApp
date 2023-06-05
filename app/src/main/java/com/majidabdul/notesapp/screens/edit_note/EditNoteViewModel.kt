package com.majidabdul.notesapp.screens.edit_note

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.domain.repository.NotesRepository
import com.majidabdul.notesapp.domain.repository.NotesRepositoryImpl
import kotlinx.coroutines.launch

/**
 * Errors and Exception are left out
 * Ideally if things go wrong while running these couroutines for example saving to the database fails
 * the user has to be notified about it. We could do that by attaching a snakcbar to the view for example
 */
class EditNoteViewModel(val notesRepository: NotesRepository): ViewModel() {
    val note = mutableStateOf(Note())

    fun init(noteId: String?) {
        viewModelScope.launch {
            noteId?.let {
                note.value = notesRepository.get(it) ?: Note()
            }
        }
    }

    fun onSaveClicked(popUp: () -> Unit) {
        viewModelScope.launch {
            val note = note.value
            if (note.id.isBlank()) {
                notesRepository.add(note)
            } else {
                notesRepository.update(note)
            }
        }
        popUp()
    }


    fun onTitleChange(newValue: String) {
        note.value = note.value.copy(title = newValue)
    }

    fun onBodyChange(newValue: String) {
        note.value = note.value.copy(body = newValue)
    }
}