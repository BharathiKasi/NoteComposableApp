package com.example.notecomposeapp.feature_note.domain.repository

import com.example.notecomposeapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(pNote:Note)

    suspend fun getNoteById(pNoteId:String):Note?

    suspend fun deleteNote(pNote:Note)

    fun getAllNotes():Flow<List<Note>>
}