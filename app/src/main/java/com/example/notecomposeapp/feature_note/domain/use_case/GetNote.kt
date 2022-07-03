package com.example.notecomposeapp.feature_note.domain.use_case

import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository

class GetNote(private val pRepository: NoteRepository) {
    suspend operator fun invoke(pNoteId:String):Note?{
       return pRepository.getNoteById(pNoteId = pNoteId)
    }
}