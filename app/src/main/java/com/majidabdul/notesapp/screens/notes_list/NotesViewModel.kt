package com.majidabdul.notesapp.screens.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.domain.repository.NotesRepositoryImpl
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel() {
    private val notesCollection = FirebaseFirestore.getInstance().collection("notes")
    private val notesRepository = NotesRepositoryImpl(notesCollection)

    fun getNotes() = notesRepository.getNotes()


    fun onAddClick(openEditNote: (Note?) -> Unit) {
        openEditNote(null)
    }
}