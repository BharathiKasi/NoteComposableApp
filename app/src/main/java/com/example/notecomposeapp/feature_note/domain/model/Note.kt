package com.example.notecomposeapp.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Note(
    val pTitle:String,
    val pContent:String,
    val pTimeStamp:Long,
    val pColor:Int,
    @PrimaryKey
    val pId:Int? = null

){
    companion object{
        val availableColors = listOf(Color.Black,Color.Blue,Color.DarkGray,Color.Green,Color.LightGray)
    }
}

class NoteInvalidException(pMessage:String) : Exception(pMessage)
