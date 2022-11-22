package com.example.notof.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notof.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getDao():NoteDao
    companion object{
        private lateinit var INSTANCE:NoteDataBase

        fun getDbInstance(context: Context):NoteDataBase{
                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDataBase::class.java,
                    "DB NAME"
                ).allowMainThreadQueries().build()
            return INSTANCE
        }
    }
}