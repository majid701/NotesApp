package com.majidabdul.notesapp.domain.repository
import com.majidabdul.notesapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun add(note: Note): String

    suspend fun get(noteId: String): Note?

    suspend fun update(note: Note)

    suspend fun delete(noteId: String)
}