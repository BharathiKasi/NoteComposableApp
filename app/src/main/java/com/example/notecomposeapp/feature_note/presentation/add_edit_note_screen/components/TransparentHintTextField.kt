package com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import java.time.format.TextStyle

@Composable
fun TransparentHintTextField(
    pText:String,
    pHint:String,
    isHintVisible:Boolean = true,
    isSingleLine:Boolean = false,
    onValueChange: (String) -> Unit,
    onFocusChage: (FocusState)->Unit,
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle()
){
    Box(modifier= modifier){
        BasicTextField(
            value = pText,
            singleLine =  isSingleLine,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth()
                .onFocusChanged {
                    onFocusChage(it)
                })
        if(isHintVisible){
            Text(text = pHint, style = textStyle, color = Color.DarkGray)
        }
    }

}