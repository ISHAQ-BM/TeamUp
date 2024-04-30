package com.example.teamup.main.presentation.ui.activity


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.teamup.R
import com.example.teamup.databinding.ActivityMainBinding
import com.example.teamup.main.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null


    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)




        viewModel.getCurrentUser()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController



        navHostFragment.findNavController().addOnDestinationChangedListener { _ , destination , _ ->
            when(destination.id){
                R.id.homeFragment -> showBottomNav()
                R.id.workspaceFragment -> showBottomNav()
                R.id.chatFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isUserLoggedIn) {
                        navController.navigate(R.id.bottom_nav_graph)
                    }
                }
            }
        }

        binding?.bottomNavigation?.setupWithNavController(navHostFragment.findNavController())

    }
    private fun showBottomNav() {
        binding?.bottomNavigation?.visibility= View.VISIBLE

    }

    private fun hideBottomNav() {
        binding?.bottomNavigation?.visibility = View.GONE

    }


        


}
