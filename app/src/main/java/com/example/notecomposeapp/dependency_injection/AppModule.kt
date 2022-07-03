package com.example.notecomposeapp.dependency_injection

import android.app.Application
import androidx.room.Room
import com.example.notecomposeapp.feature_note.data.data_source.NoteDatabase
import com.example.notecomposeapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository
import com.example.notecomposeapp.feature_note.domain.use_case.DeleteNote
import com.example.notecomposeapp.feature_note.domain.use_case.GetNotes
import com.example.notecomposeapp.feature_note.domain.use_case.NoteUseCases
import com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.AddNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesNoteDataBase(pApplication:Application): NoteDatabase{
        return Room.databaseBuilder(pApplication,
            NoteDatabase::class.java,
            NoteDatabase.DATEBASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(pNoteDatabase: NoteDatabase):NoteRepository{
        return  NoteRepositoryImpl(pNoteDatabase.mNoteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(pNoteRepository: NoteRepository) : NoteUseCases{
        return NoteUseCases(pDeleteNote = DeleteNote(pNoteRepository = pNoteRepository), pGetNotes = GetNotes(pNoteRepository = pNoteRepository), pAddNote = AddNote(pNoteRepository = pNoteRepository))
    }
}