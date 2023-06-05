package com.majidabdul.notesapp.notes_list

import com.majidabdul.notesapp.domain.model.Note
import com.majidabdul.notesapp.repository.FakeNoteRepository
import com.majidabdul.notesapp.screens.notes_list.NotesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * This is just a start with testing to show how a viewmodel can be tested
 * Because of time constraints complete test coverage is not implemented
 */
class NotesViewModelTest {

    private lateinit var viewModel: NotesViewModel
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        viewModel = NotesViewModel(fakeNoteRepository)

        val mockNotes = mutableListOf<Note>()
        ('A'..'Z').forEachIndexed { index, char ->
            mockNotes.add(
                Note(id = index.toString(), title = char.toString(), body = char.toString())
            )
        }

        runBlocking {
            mockNotes.forEach { fakeNoteRepository.add(it) }
        }
    }

    @Test
    fun `Should return correct size note list`() = runBlocking {
        val notes = viewModel.getNotes().first()
        assertEquals(26, notes.size)
    }

    @Test
    fun `Should remove note from list`() = runBlocking {
        val note = viewModel.getNotes().first().random()
        viewModel.onDeleteClick(note)

        val notesAfterRemoval = viewModel.getNotes().first()
        assertEquals(25, notesAfterRemoval.size)
    }

    @Test
    fun `Ensure correct note is removed from list`() = runBlocking {
        val note = viewModel.getNotes().first().random()
        viewModel.onDeleteClick(note)

        val notesAfterRemoval = viewModel.getNotes().first()
        assertEquals(null, notesAfterRemoval.find { it.id == note.id })
    }

}