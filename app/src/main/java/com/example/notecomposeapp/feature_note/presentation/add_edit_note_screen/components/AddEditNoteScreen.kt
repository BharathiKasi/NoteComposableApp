package com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.components

import android.graphics.drawable.Icon
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.notecomposeapp.feature_note.domain.model.Note
import com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.AddEditNoteEvent
import com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.AddEditNoteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    pNavController: NavController,
    viewmodel : AddEditNoteViewModel = hiltViewModel(),
    pNoteColor: Int
) {
    val mTitleState = viewmodel.mNoteTitleState.value
    val mContentState = viewmodel.mNoteContentState.value
    val mScaffoldState = rememberScaffoldState()
    val mNoteBackgroundAnimatable = remember {
        Animatable(initialValue = Color( if(pNoteColor!=-1) pNoteColor else viewmodel.mNoteColorState.value))
    }
    val lScope = rememberCoroutineScope()
    LaunchedEffect(key1 =true ){
        viewmodel.mEventFlow.collectLatest { event->
            when(event){
                is AddEditNoteViewModel.UiEvent.ShowSnackBar->{
                    mScaffoldState.snackbarHostState.showSnackbar(message = event.pMessage)
                }
                is AddEditNoteViewModel.UiEvent.SaveNote->{
                    pNavController.navigateUp()
                }
            }

        }
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            viewmodel.onEvent(AddEditNoteEvent.SaveNote)
        }, backgroundColor = MaterialTheme.colors.primary) {
            androidx.compose.material.Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note" )
        }
    },
    scaffoldState = mScaffoldState) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(mNoteBackgroundAnimatable.value)
            .padding(16.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Note.availableColors.forEach { color->
                    val colorInt = color.toArgb()
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(16.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color = color)
                        .border(
                            width = 3.dp,
                            color = if (viewmodel.mNoteColorState.value == colorInt)
                                Color.Black else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            lScope.launch {
                                mNoteBackgroundAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(durationMillis = 500)
                                )
                                viewmodel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                        })
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(pText = mTitleState.pText, pHint =mTitleState.pHint , onValueChange = {
                   viewmodel.onEvent(AddEditNoteEvent.EnteredTitle(mTitleState.pText))
            }, onFocusChage ={
                viewmodel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))

            },
            isHintVisible = mTitleState.isHintVisible,
                isSingleLine = true,
                modifier = Modifier ,
                textStyle = MaterialTheme.typography.h5
                )
            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(pText = mContentState.pText, pHint =mContentState.pHint , onValueChange = {
                viewmodel.onEvent(AddEditNoteEvent.EnteredContent(mContentState.pText))
            }, onFocusChage ={
                viewmodel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))

            },
                isHintVisible = mContentState.isHintVisible,
                isSingleLine = false,
                modifier = Modifier ,
                textStyle = MaterialTheme.typography.h5
            )
            
        }
    }
}