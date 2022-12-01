package com.example.notof.ui.utils

import android.app.Application
import android.content.SharedPreferences
import com.example.notof.data.NoteDataBase

class App: Application() {
    private lateinit var preferences: SharedPreferences
    companion object{
        lateinit var db:NoteDataBase
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = NoteDataBase.getDbInstance(this)
        preferences = this.applicationContext
            .getSharedPreferences("setting", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}