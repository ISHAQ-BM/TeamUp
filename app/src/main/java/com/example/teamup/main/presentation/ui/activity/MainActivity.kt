package com.example.teamup.main.presentation.ui.activity


import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.root?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { view, windowInsets ->

                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())


                view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
                    leftMargin = insets.left
                    bottomMargin = insets.bottom
                    rightMargin = insets.right
                    topMargin = insets.top
                }

                WindowInsetsCompat.CONSUMED
            }
        }


        viewModel.getCurrentUser()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isUserLoggedIn) {
                        navController.navigate(R.id.bottom_nav_graph)
                    }
                }
            }
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController



        navHostFragment.findNavController().addOnDestinationChangedListener { _ , destination , _ ->
            when(destination.id){
                R.id.homeFragment -> showBottomNav()
                R.id.workspaceFragment -> showBottomNav()
                R.id.chatFragment -> showBottomNav()
                R.id.accountFragment -> showBottomNav()
                else -> hideBottomNav()
            }

        }


        binding?.bottomNavigation?.setupWithNavController(navHostFragment.findNavController())

    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        hideKeyboard(event)
        return super.dispatchTouchEvent(event)
    }
    private fun showBottomNav() {
        binding?.bottomNavigation?.visibility= View.VISIBLE

    }

    private fun hideBottomNav() {
        binding?.bottomNavigation?.visibility = View.GONE

    }

    private fun hideKeyboard(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null) {
                view.clearFocus()
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    val inputMethodManager =
                        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

                    inputMethodManager.hideSoftInputFromWindow(
                        view.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        }
    }


        


}
