package com.example.notof.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.airbnb.lottie.L
import com.example.notof.model.NoteModel

@Dao
interface NoteDao {
    @Query( "SELECT * FROM notemodel")
    fun getAllNote():List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun DeleteNote(model: NoteModel)

    @Update
    fun upNote(model: NoteModel)
}