package com.example.notof.ui.utils

import android.content.SharedPreferences

class Prefs(private val preferences :SharedPreferences) {
    fun seveBoardStart(){
        preferences.edit().putBoolean("isShow",true).apply()
    }

    fun isBoartShow(): Boolean{
        return preferences.getBoolean("isShow",false)
    }
}