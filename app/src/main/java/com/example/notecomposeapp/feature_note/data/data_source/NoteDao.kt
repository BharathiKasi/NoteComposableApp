package com.example.notecomposeapp.feature_note.data.data_source

import androidx.room.*
import com.example.notecomposeapp.feature_note.domain.model.Note
import java.util.concurrent.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(pNote:Note)

    @Delete
    suspend fun deleteNote(pNote: Note)
    @Query("SELECT * FROM note WHERE pId = :pNoteId")
    suspend fun getNoteById(pNoteId:String):Note

    fun getAllNotes():kotlinx.coroutines.flow.Flow<List<Note>>
}