package com.example.notecomposeapp.feature_note.domain.use_case

import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository

class DeleteNote(val pNoteRepository: NoteRepository) {
    suspend operator fun invoke(pNote: Note){
        pNoteRepository.deleteNote(pNote = pNote)

    }
}