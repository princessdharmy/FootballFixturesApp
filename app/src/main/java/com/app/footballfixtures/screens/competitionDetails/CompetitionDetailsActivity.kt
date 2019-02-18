package com.app.footballfixtures.screens.competitionDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.PlayerResponse
import com.app.footballfixtures.databinding.ActivityCompetitionDetailsBinding
import com.app.footballfixtures.screens.bottomSheet.BottomSheetFragment
import com.app.footballfixtures.screens.competitionDetails.fixturesFragment.FixturesFragment
import com.app.footballfixtures.screens.competitionDetails.tableFragment.TableFragment
import com.app.footballfixtures.screens.competitionDetails.teamFragment.TeamFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_competition_details.*
import javax.inject.Inject
import javax.inject.Named


class CompetitionDetailsActivity : DaggerAppCompatActivity(), TeamFragment.OnFragmentInteractionListener {

    companion object {
        var competitionId: Long? = 0L
        var name: String? = ""
    }

    lateinit var binding: ActivityCompetitionDetailsBinding
    private var bottomSheetFragment: BottomSheetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_competition_details)

        getIntents()
        binding.toolbar.title = name
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(TableFragment().apply {
            arguments = Bundle().apply {
                putLong("id", competitionId ?: 0L)
            }
        }, "Table")
        adapter.addFragment(FixturesFragment().apply {
            arguments = Bundle().apply {
                putLong("id", competitionId ?: 0L)
            }
        }, "Fixtures")
        adapter.addFragment(TeamFragment().apply {
            arguments = Bundle().apply {
                putLong("id", competitionId ?: 0L)
            }
        }, "Teams")

        // Set up the ViewPager with the sections adapter.
        binding.container.adapter = adapter
        binding.tabs.setupWithViewPager(binding.container)
    }

    private fun getIntents() {
        competitionId = intent?.extras?.getLong("id")
        name = intent?.extras?.getString("name")
    }

    override fun sendTeam(playerResponse: PlayerResponse) {
        if (bottomSheetFragment == null) {
            bottomSheetFragment = BottomSheetFragment.newInstance(playerResponse)
            bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment?.tag)
        } else {
            bottomSheetFragment?.updateContent(playerResponse)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
