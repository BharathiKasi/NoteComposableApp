package com.example.notecomposeapp.feature_note.data.repository

import com.example.notecomposeapp.feature_note.data.data_source.NoteDao
import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val pDao:NoteDao) : NoteRepository {
    override suspend fun insertNote(pNote: Note) {
        pDao.insertNote(pNote = pNote)
    }

    override suspend fun getNoteById(pNoteId: String):Note? {
        return pDao.getNoteById(pNoteId = pNoteId)
    }

    override suspend fun deleteNote(pNote: Note) {
        pDao.deleteNote(pNote = pNote)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return pDao.getAllNotes()
    }
}