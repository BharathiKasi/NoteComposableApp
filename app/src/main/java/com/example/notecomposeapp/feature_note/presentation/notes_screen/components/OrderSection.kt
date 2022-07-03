package com.example.notecomposeapp.feature_note.presentation.notes_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notecomposeapp.feature_note.domain.NoteOrderType
import com.example.notecomposeapp.feature_note.domain.OrderType

@Composable
fun OrderSection(pModifier:Modifier = Modifier,
                 pNoteOrderType: NoteOrderType = NoteOrderType.Date(OrderType.Descending),
                 pOrderChange:(pNoteOrderType:NoteOrderType)->Unit
                 ){
    
    Column(modifier =  pModifier) {
        Row(modifier = pModifier.fillMaxWidth()) {
            DefaultRadioButton(pText = "Title",
                pIsSelected = pNoteOrderType is NoteOrderType.Title,
                onClick = { pOrderChange(NoteOrderType.Title(pNoteOrderType.pOrderType))
                }
            )
            Spacer(modifier = pModifier.width(8.dp))

            DefaultRadioButton(pText = "Date",
                pIsSelected = pNoteOrderType is NoteOrderType.Date,
                onClick = { pOrderChange(NoteOrderType.Date(pNoteOrderType.pOrderType))
                }
            )
            Spacer(modifier = pModifier.width(8.dp))

            DefaultRadioButton(pText = "Color",
                pIsSelected = pNoteOrderType is NoteOrderType.Color,
                onClick = { pOrderChange(NoteOrderType.Color(pNoteOrderType.pOrderType))
                }
            )
        }
        Spacer(modifier = pModifier.height(10.dp))
        Row(modifier = pModifier) {
            Column(modifier = pModifier.fillMaxWidth()) {
                DefaultRadioButton(pText = "Ascending",
                    pIsSelected = pNoteOrderType.pOrderType is OrderType.Ascesding,
                    onClick = {pNoteOrderType.copy(OrderType.Ascesding)
                    }
                )
                Spacer(modifier = pModifier.width(8.dp))

                DefaultRadioButton(pText = "Descending",
                    pIsSelected = pNoteOrderType.pOrderType is OrderType.Descending,
                    onClick = { pNoteOrderType.copy(OrderType.Descending)
                    }
                )
                Spacer(modifier = pModifier.width(8.dp))

            }

            }

        }
        
    }
    
}