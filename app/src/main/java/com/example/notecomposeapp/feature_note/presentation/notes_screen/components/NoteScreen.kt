package com.example.notecomposeapp.feature_note.presentation.notes_screen.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notecomposeapp.feature_note.presentation.notes_screen.NoteEvent
import com.example.notecomposeapp.feature_note.presentation.notes_screen.NotesViewModel
import com.example.notecomposeapp.feature_note.presentation.utill.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteScree(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
){
    val state = viewModel._mNoteState.value
    val lScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
                                       navController.navigate(Screen.AddEditNoteScreen.route)
        },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
        } }
    , scaffoldState = lScaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Your Notes",
                    style = MaterialTheme.typography.h4
                    )
                IconButton(onClick = {
                    viewModel.onEvent(NoteEvent.ToggleOrderSelection)
                }) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort by order")
                    
                }
                
            }

        }
        AnimatedVisibility(visible = state.pIsOrderSectionVisible,
            enter = fadeIn()+ slideInVertically(),
            exit = fadeOut() + slideOutVertically()
            ) {
            OrderSection(pOrderChange = {
                viewModel.onEvent(NoteEvent.Order(it))
            }, pModifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), pNoteOrderType = state.pNoteOrderType)
        }
        Spacer(modifier = Modifier.height(17.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.pNoteList){note ->
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                                   navController.navigate(Screen.AddEditNoteScreen.route+"?noteid=${note.pId}&noteColor=${note.pColor}")
                        },
                    pNote = note,
                    onDeleteClick = {
                        viewModel.onEvent(NoteEvent.DeleteNote(note))
                        scope.launch {
                           val lSnackBarResult =  lScaffoldState.snackbarHostState.showSnackbar(message = "Note deleted",actionLabel = "UNDO")
                            if(lSnackBarResult==SnackbarResult.ActionPerformed){
                                viewModel.onEvent(NoteEvent.RestoreNote)
                            }
                        }
                    })
                Spacer(modifier = Modifier.height(17.dp))
            }
        }
    }

}