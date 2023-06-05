package com.majidabdul.notesapp.screens.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.domain.repository.NotesRepository
import com.majidabdul.notesapp.domain.repository.NotesRepositoryImpl
import kotlinx.coroutines.launch

class NotesViewModel(val notesRepository: NotesRepository): ViewModel() {

    /*
     * Some sort of screen UI state logic should be implemented here as well so we can show the user
     * Loading -> View results
     * Loading -> Error
     * Loading -> Empty View
     */

    fun getNotes() = notesRepository.getNotes()


    fun onAddClick(openEditNote: (Note?) -> Unit) {
        openEditNote(null)
    }

    fun onDeleteClick(note: Note) {
        viewModelScope.launch {
            notesRepository.delete(note.id)
        }
    }
}