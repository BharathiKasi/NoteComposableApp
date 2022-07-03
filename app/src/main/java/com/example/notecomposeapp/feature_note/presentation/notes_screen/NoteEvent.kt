package com.example.notecomposeapp.feature_note.presentation.notes_screen

import com.example.notecomposeapp.feature_note.domain.NoteOrderType
import com.example.notecomposeapp.feature_note.domain.model.Note

sealed class NoteEvent{
    data class Order(val pNoteOrderType: NoteOrderType): NoteEvent()
    data class DeleteNote(val pNote:Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSelection : NoteEvent()
}
