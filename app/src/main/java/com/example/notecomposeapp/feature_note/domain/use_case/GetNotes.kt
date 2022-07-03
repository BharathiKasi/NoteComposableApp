package com.example.notecomposeapp.feature_note.domain.use_case

import com.example.notecomposeapp.feature_note.domain.NoteOrderType
import com.example.notecomposeapp.feature_note.domain.OrderType
import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(val pNoteRepository: NoteRepository) {

    operator fun invoke(pNoteOrderType: NoteOrderType = NoteOrderType.Date(OrderType.Descending)): Flow<List<Note>> {
        return pNoteRepository.getAllNotes().map { notes->
            when(pNoteOrderType.pOrderType){

                is OrderType.Ascesding ->{
                   when(pNoteOrderType){
                       is NoteOrderType.Title-> notes.sortedBy { it.pTitle.lowercase() }
                        is NoteOrderType.Date-> notes.sortedBy { it.pTimeStamp }
                       is NoteOrderType.Color -> notes.sortedBy { it.pColor }
                   }
                }
                is OrderType.Descending ->{
                    when(pNoteOrderType){
                       is NoteOrderType.Title -> notes.sortedByDescending { it.pTitle.lowercase() }
                        is NoteOrderType.Date -> notes.sortedByDescending { it.pTimeStamp }
                       is  NoteOrderType.Color -> notes.sortedByDescending { it.pColor }
                    }
                }
            }

        }
    }
}