package com.example.notecomposeapp.feature_note.domain

sealed class NoteOrderType(val pOrderType:OrderType){
     class Title(pOrderType:OrderType) : NoteOrderType( pOrderType)
     class Date(pOrderType:OrderType) : NoteOrderType(pOrderType)
     class Color(pOrderType:OrderType) : NoteOrderType(pOrderType)

     fun copy(pOrderType: OrderType){
          when(this){
               is Title ->{
                    Title(pOrderType = pOrderType)
               }
               is Date ->{
                    Date(pOrderType = pOrderType)

               }
               is Color ->{
                    Date(pOrderType = pOrderType)

               }
          }
     }
}
