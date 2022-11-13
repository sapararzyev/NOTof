package com.example.notof.iu

import android.app.Application
import android.content.SharedPreferences
import com.example.notof.Data.NoteDataBase
import com.example.notof.iu.Utils.Prefs

class App : Application() {
    private lateinit var preferences:SharedPreferences

    companion object{
       lateinit var db:NoteDataBase
       lateinit var prefs : Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = NoteDataBase.getDbInstance(this)
        preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)

    }
}