package com.example.notecomposeapp.feature_note.presentation.utill

sealed class Screen(val route:String){
    object NoteScreen : Screen("note_scree")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
}
