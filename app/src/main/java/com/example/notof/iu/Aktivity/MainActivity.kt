package com.example.notof.iu.Aktivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.notof.R
import com.example.notof.iu.App

class MainActivity : AppCompatActivity() {
    private lateinit var controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
        if (!App.prefs.isBoartShow()){
            controller.navigate(R.id.onBoardFragment)
        }
    }
}