package com.example.notecomposeapp.feature_note.domain.use_case

import com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.AddNote

data class NoteUseCases(val pDeleteNote: DeleteNote,
                        val pGetNotes: GetNotes,
                        val pAddNote: AddNote
)
