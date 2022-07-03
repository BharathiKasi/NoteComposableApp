package com.example.notecomposeapp.feature_note.presentation.notes_screen.components

import android.widget.Space
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(pText:String,
                       pIsSelected:Boolean,
                       onClick: () -> Unit,
                       pModifier:androidx.compose.ui.Modifier = androidx.compose.ui.Modifier) {
    Row(modifier = pModifier, verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = pIsSelected, onClick = {
            onClick()
        }, colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary, unselectedColor = MaterialTheme.colors.background))
    }
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = pText, style = MaterialTheme.typography.body1)

}