package com.example.teamup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.teamup.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding?.bottomNavigation?.setupWithNavController(navHostFragment.findNavController())


        navHostFragment.findNavController().addOnDestinationChangedListener { _ , destination , _ ->
            when(destination.id){
                R.id.homeFragment -> showBottomNav()
                R.id.exploreFragment -> showBottomNav()
                R.id.chatFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }

        }
    }
    private fun showBottomNav() {
        binding?.bottomNavigation?.visibility= View.VISIBLE

    }

    private fun hideBottomNav() {
        binding?.bottomNavigation?.visibility = View.GONE

    }


        


}
