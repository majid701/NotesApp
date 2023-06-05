package com.majidabdul.notesapp.repository

import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository: NotesRepository {

    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun add(note: Note): String {
        notes.add(note)
        return note.id
    }

    override suspend fun get(noteId: String): Note? {
        return notes.find { it.id == noteId }
    }

    override suspend fun update(note: Note) {
        notes[notes.indexOf(note)] = note
    }

    override suspend fun delete(noteId: String) {
        notes.removeIf { it.id == noteId }
    }
}