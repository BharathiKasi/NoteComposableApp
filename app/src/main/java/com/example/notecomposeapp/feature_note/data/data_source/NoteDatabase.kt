package com.example.notecomposeapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notecomposeapp.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    companion object{
        const val DATEBASE_NAME = "note_db"
    }
    abstract val mNoteDao:NoteDao

}