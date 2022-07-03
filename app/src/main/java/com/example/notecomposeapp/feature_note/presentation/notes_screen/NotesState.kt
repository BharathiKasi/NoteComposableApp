package com.example.notecomposeapp.feature_note.presentation.notes_screen

import com.example.notecomposeapp.feature_note.domain.NoteOrderType
import com.example.notecomposeapp.feature_note.domain.OrderType
import com.example.notecomposeapp.feature_note.domain.model.Note

data class NotesState(val pNoteList:List<Note> = emptyList(),
                      val pNoteOrderType :NoteOrderType = NoteOrderType.Date(OrderType.Descending),
                      val pIsOrderSectionVisible:Boolean = false
                      )
