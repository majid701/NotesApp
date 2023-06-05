package com.majidabdul.notesapp.domain.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.majidabdul.notesapp.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class NotesRepositoryImpl(
    private val firestore: FirebaseFirestore
) : NotesRepository {

    /**
     * There are no firestore security rules set
     * In a production app we will have rules to protect the database so the notes collection should
     * be connected to some user (Firebase Authentication can be used for that) and only that user should be
     * allow to perform CRUD operations on its own notes collection
     */


    /**
     * Offline functionality can be achieved as firestore is offline available database
     * Only snapshot listeneres must be used then as .get() only fetches documents from the server
     */
    override fun getNotes(): Flow<List<Note>> {
        return firestore.collection(NOTES_COLLECTION).snapshots().map { it.toObjects() }
    }

    override suspend fun add(note: Note): String {
        return firestore.collection(NOTES_COLLECTION).add(note).await().id
    }

    override suspend fun get(noteId: String): Note? {
        return firestore.collection(NOTES_COLLECTION).document(noteId).get().await().toObject()
    }

    override suspend fun update(note: Note) {
        firestore.collection(NOTES_COLLECTION).document(note.id).set(note).await()
    }

    override suspend fun delete(noteId: String) {
        firestore.collection(NOTES_COLLECTION).document(noteId).delete().await()
    }

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }
}