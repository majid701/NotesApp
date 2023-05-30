package com.majidabdul.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val notesCollection = FirebaseFirestore.getInstance().collection("notes")
    private val notesRepository = NotesRepositoryImpl(notesCollection)

    fun getNotes() = notesRepository.getNotes()
}