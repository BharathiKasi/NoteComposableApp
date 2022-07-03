package com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen

import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.model.NoteInvalidException
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository

class AddNote(private val pNoteRepository: NoteRepository) {

    @Throws(NoteInvalidException::class)
    suspend operator fun invoke(pNote:Note){
        if(pNote.pTitle.isBlank()){
            throw NoteInvalidException("title is blank")
        }
        pNoteRepository.insertNote(pNote = pNote)
    }
}