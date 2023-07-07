package com.achsanit.footballstandings.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.achsanit.footballstandings.R
import com.achsanit.footballstandings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavBar()
    }

    private fun setUpNavBar() {
        val navBar = binding.navigationBar
        val navController = findNavController(R.id.nav_host_fragment_main)

        navBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {  _, destination, _ ->
            when(destination.id) {
                R.id.nav_data_club -> setUpBottomNav(true)
                R.id.nav_score -> setUpBottomNav(true)
                R.id.nav_standings -> setUpBottomNav(true)
                else -> setUpBottomNav(false)
            }
        }
    }

    private fun setUpBottomNav(isVisible: Boolean) {
        with(binding) {
            if (isVisible) {
                navigationBar.visibility = View.VISIBLE
            } else {
                navigationBar.visibility = View.GONE
            }
        }
    }
}