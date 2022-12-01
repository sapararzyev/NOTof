package com.example.notof.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notof.model.NoteModel

@Database(entities = [NoteModel::class], version = 2, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getDao():NoteDao
    companion object{
        private lateinit var INSTANCE:NoteDataBase

        fun getDbInstance(context: Context):NoteDataBase{
                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDataBase::class.java,
                    "DB NAME"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            return INSTANCE
        }
    }
}