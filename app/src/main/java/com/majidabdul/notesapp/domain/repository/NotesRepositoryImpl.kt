package com.majidabdul.notesapp.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class NotesRepositoryImpl(
    private val notesRef: CollectionReference
) : NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return notesRef.snapshots().map { it.toObjects() }
    }

    override suspend fun add(note: Note): String {
        return notesRef.add(note).await().id
    }

    override suspend fun get(noteId: String): Note? {
        return notesRef.document(noteId).get().await().toObject()
    }

    override suspend fun update(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(noteId: String) {
        TODO("Not yet implemented")
    }

}