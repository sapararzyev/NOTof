package com.example.notof.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title: String,
    val description:String,
    val data: String
)
