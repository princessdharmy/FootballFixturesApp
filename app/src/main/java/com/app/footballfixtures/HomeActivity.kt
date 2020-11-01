package com.app.footballfixtures

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.app.footballfixtures.databinding.ActivityHomeBinding
import com.example.common.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setSupportActionBar(binding.toolbar)
        initBinding()
    }

    private fun initBinding() {
        // Finding the Navigation Controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        navController = navHostFragment.navController

        // Setting Navigation Controller with the BottomNavigationView
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        //Setting Up ActionBar with Navigation Controller
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.todayFixtureFragment, R.id.competitionsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        // This helps to customise the toolbar i.e the back button
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.viewPager) {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            }
        }

    }

    /**
     * This enables click listener on the back button
     */
    override fun onSupportNavigateUp() =
        findNavController(R.id.fragNavHost).navigateUp(appBarConfiguration)

}
