package com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val pTitle:String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val pFocusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val pContent: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val pFocusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val pColor:Int) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}