package com.example.notecomposeapp.feature_note.presentation.notes_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notecomposeapp.feature_note.domain.NoteOrderType
import com.example.notecomposeapp.feature_note.domain.OrderType
import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val pNoteUseCases: NoteUseCases): ViewModel(){
    private val mNoteMState  = mutableStateOf(NotesState())
    val _mNoteState  = mNoteMState
    private var mGetNoteJob : Job? = null
    private var mRecentlyDeletedNote: Note? = null
    init {
        getNotes(NoteOrderType.Date(OrderType.Descending))
    }

    fun onEvent(pEvent:NoteEvent){
        when(pEvent){
            is NoteEvent.Order ->{
                if(mNoteMState.value.pNoteOrderType::class == pEvent.pNoteOrderType::class &&
                        mNoteMState.value.pNoteOrderType.pOrderType == pEvent.pNoteOrderType.pOrderType
                        ){
                    return

                }else{

                }
            }
            is NoteEvent.DeleteNote ->{
                viewModelScope.launch {
                    pNoteUseCases.pDeleteNote(pEvent.pNote)
                    mRecentlyDeletedNote = pEvent.pNote
                }
            }
            is NoteEvent.RestoreNote ->{
                viewModelScope.launch {
                    mRecentlyDeletedNote?.let { restoringNote->
                        pNoteUseCases.pAddNote(restoringNote)
                        mRecentlyDeletedNote = null
                    }
                }

            }
            is NoteEvent.ToggleOrderSelection ->{
                _mNoteState.value = mNoteMState.value.copy(pIsOrderSectionVisible = !mNoteMState.value.pIsOrderSectionVisible)
            }
        }
    }

    private fun getNotes(pNoteOrderType: NoteOrderType){
        mGetNoteJob?.cancel()
        mGetNoteJob = pNoteUseCases.pGetNotes(pNoteOrderType = pNoteOrderType).onEach { pNoteList->
            _mNoteState.value = mNoteMState.value.copy(pNoteList = pNoteList, pNoteOrderType = pNoteOrderType)
        }.launchIn(viewModelScope)
    }
}