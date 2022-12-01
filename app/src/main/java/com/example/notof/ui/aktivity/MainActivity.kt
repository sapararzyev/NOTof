package com.example.notof.ui.aktivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notof.R
import com.example.notof.databinding.ActivityMainBinding
import com.example.notof.ui.utils.App
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var controller : NavController
    private lateinit var binding : ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            .also { setContentView(it.root) }
        navView = binding.bottomNav
        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController

         val appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.noteFragment,
                 R.id.contackFragment,
                 R.id.pofileFragment
             )
         )
        setupActionBarWithNavController(controller,appBarConfiguration)
        navView.setupWithNavController(controller)

        if (!App.prefs.isBoartShow()){
            controller.navigate(R.id.onBoardFragment)
        }

        controller. addOnDestinationChangedListener{_,destination, _ ->
            val list: ArrayList<Int> = arrayListOf()
            list.add(R.id.contackFragment)
            list.add(R.id.noteFragment)
            list.add(R.id.pofileFragment)
            if (list.contains(destination.id )){
                binding.bottomNav.isVisible = true
            }else{
                binding.bottomNav.isGone = false
            }
        }
    }
}