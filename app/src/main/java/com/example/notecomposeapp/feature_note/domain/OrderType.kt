package com.example.notecomposeapp.feature_note.domain

sealed class OrderType(){
    object Ascesding : OrderType()
    object Descending : OrderType()
}
