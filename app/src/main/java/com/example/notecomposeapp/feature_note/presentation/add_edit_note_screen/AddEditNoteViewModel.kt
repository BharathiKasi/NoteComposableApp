package com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditNoteViewModel @Inject constructor(
    private val pNoteUseCases: NoteUseCases,
    private val pSavedStateHandle: SavedStateHandle
    ) : ViewModel() {

    private val mNoteTitleMState = mutableStateOf(NoteTextFieldState())
    val mNoteTitleState = mNoteTitleMState

    private val mNoteContentMState = mutableStateOf(NoteTextFieldState())
    val mNoteContentState = mNoteContentMState

    private val mNoteColorMState = mutableStateOf(Note.availableColors.random().toArgb())
    val mNoteColorState = mNoteColorMState

    private val mEventMFlow = MutableSharedFlow<UiEvent>()
    val mEventFlow = mEventMFlow

    private var mCurrentNoteId:Int? = null

    sealed class UiEvent{
        data class ShowSnackBar(val pMessage:String) : UiEvent()
        object SaveNote : UiEvent()
    }

    init {
        pSavedStateHandle.get<Int>("noteId")?.let { noteId->
            if(noteId!=-1){
                viewModelScope.launch {
                    pNoteUseCases.pGetNote(noteId.toString())?.also { note->
                       mCurrentNoteId = note.pId
                        mNoteTitleState.value= mNoteTitleMState.value.copy(pText = note.pTitle, isHintVisible = false)
                        mNoteContentState.value = mNoteContentMState.value.copy(pText = note.pContent, isHintVisible = false)
                        mNoteColorState.value = note.pColor
                    }
                }
            }

        }
    }
    fun onEvent(pEvent:AddEditNoteEvent){
        when(pEvent){
            is AddEditNoteEvent.EnteredTitle ->{
                mNoteTitleState.value = mNoteTitleMState.value.copy(pText = pEvent.pTitle)
            }
            is AddEditNoteEvent.EnteredContent ->{
                mNoteContentState.value = mNoteContentMState.value.copy(pText = pEvent.pContent)
            }
            is AddEditNoteEvent.ChangeTitleFocus ->{
                mNoteTitleState.value = mNoteTitleMState.value.copy(isHintVisible = (!pEvent.pFocusState.isFocused) && mNoteTitleMState.value.pText.isBlank())
            }
            is AddEditNoteEvent.ChangeContentFocus ->{
                mNoteContentState.value = mNoteContentMState.value.copy(isHintVisible = (!pEvent.pFocusState.isFocused) && mNoteContentMState.value.pText.isBlank())
            }
            is AddEditNoteEvent.ChangeColor ->{
                mNoteColorState.value = pEvent.pColor
            }
            is AddEditNoteEvent.SaveNote ->{
                viewModelScope.launch {
                    try{
                        pNoteUseCases.pAddNote(
                            Note(
                                mNoteTitleState.value.pText,
                                mNoteContentState.value.pText,
                                pTimeStamp = System.currentTimeMillis(),
                                mNoteColorState.value,
                            pId = mCurrentNoteId
                            )
                        )
                        mEventMFlow.emit(UiEvent.SaveNote)
                    }catch (pException:Exception){
                        mEventMFlow.emit(UiEvent.ShowSnackBar(pException.message ?: "Couldn't save note!"))
                        pException.printStackTrace()
                    }
                }
            }
        }
    }




}