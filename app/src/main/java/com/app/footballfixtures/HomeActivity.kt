package com.app.footballfixtures

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.footballfixtures.databinding.ActivityHomeBinding
import com.example.common.base.BaseActivity
import com.example.competitions.competitions.CompetitionsFragment
import com.example.competitions.today.TodayFixturesFragment

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setSupportActionBar(binding.toolbar)
        initBinding()
    }

    private fun initBinding(){
        // Finding the Navigation Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        navController = navHostFragment.navController

        // Setting Navigation Controller with the BottomNavigationView
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

         //Setting Up ActionBar with Navigation Controller
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.todayFixtureFragment, R.id.competitionsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

}
