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

    /*private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_fixture -> {
                binding.toolbar.title = "Today\'s Fixtures"
                supportFragmentManager.beginTransaction()
                    .replace(frame_container.id, TodayFixturesFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_competition -> {
                binding.toolbar.title = "Competitions"
                supportFragmentManager.beginTransaction()
                    .replace(frame_container.id, CompetitionsFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initBinding()
    }

    fun initBinding(){
        // Finding the Navigation Controller
        val navController = findNavController(R.id.fragNavHost)

        // Setting Navigation Controller with the BottomNavigationView
        binding.bottomNavigation.setupWithNavController(navController)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
//        navController = navHostFragment.navController
//        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        // Setting Up ActionBar with Navigation Controller
        //setupActionBarWithNavController(navController)
//        val appBarConfiguration = AppBarConfiguration(setOf
//            (R.id.todayFixtureFragment, R.id.competitionsFragment))
//        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)

    }

}
