package com.example.notecomposeapp.feature_note.presentation.notes_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.notecomposeapp.feature_note.domain.model.Note

@Composable
fun NoteItem(modifier:Modifier = Modifier,
             pNote:Note,
             pCornerRadius:Dp = 10.dp,
             pCutCornerSize:Dp = 30.dp,
             onDeleteClick :() -> Unit
             ){
    Box(modifier = modifier){
        Canvas(modifier = Modifier.matchParentSize()){
            val lClipPath = Path().apply {
                lineTo(size.width-pCutCornerSize.toPx(),0f)
                lineTo(size.width,pCutCornerSize.toPx())
                lineTo(0f,size.height)
                lineTo(size.width,size.height)
                close()
            }
            clipPath(lClipPath){
                drawRoundRect(
                    color = Color(pNote.pColor),
                    size = size,
                    cornerRadius = CornerRadius(pCornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(pNote.pColor,0X000000,0.2f)),
                    topLeft = Offset(size.width-pCutCornerSize.toPx(),-100f),
                    size = Size(pCutCornerSize.toPx()+100f,pCornerRadius.toPx()+100f),
                    cornerRadius = CornerRadius(pCornerRadius.toPx())

                    )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)

            ) {
            Text(text = pNote.pTitle,
                style =  MaterialTheme.typography.h6,
                color = MaterialTheme.colors.surface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pNote.pContent,
                style =  MaterialTheme.typography.body1,
                color = MaterialTheme.colors.surface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = onDeleteClick,
        modifier = Modifier.align(Alignment.BottomEnd)
            ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "delete note"
            )

        }
    }

}