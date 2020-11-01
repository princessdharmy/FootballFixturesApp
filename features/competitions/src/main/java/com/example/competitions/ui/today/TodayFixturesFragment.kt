package com.example.competitions.ui.today

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.competitions.R
import com.example.competitions.databinding.TodayFixturesFragmentBinding
import com.example.competitions.di.DaggerCompetitionComponent
import com.example.core.coreComponent
import com.example.common.utils.network.NetworkStatus
import com.example.presentation.models.MatchResponse
import com.example.presentation.models.PlayerResponse
import com.example.presentation.utils.Utilities.getCurrentDate
import com.example.presentation.viewmodels.CompetitionsViewModel
import javax.inject.Inject

class TodayFixturesFragment : BaseFragment() {

    lateinit var binding: TodayFixturesFragmentBinding
    private lateinit var adapter: TodayFixturesAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.today_fixtures_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTodayMatch(getCurrentDate())
    }

    private fun initRecyclerView() {
        adapter = TodayFixturesAdapter(ArrayList())
        binding.fixturesRecyclerview.adapter = adapter
        binding.fixturesRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.fixturesRecyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    /**
     * Fetch list of all matches for today
     */
    private fun getTodayMatch(date: String) {
        if (hasInternetConnection(requireContext()))
            viewModel.getAllMatches(date).observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is NetworkStatus.Loading -> {
                        showLoading()
                    }
                    is NetworkStatus.Error -> {
                        hideLoading()
                        getTodayMatchFailed(result.errorMessage!!)
                    }
                    is NetworkStatus.Success -> {
                        hideLoading()
                        result.data?.let { getTodayMatchSuccessful(it) }
                    }
                }
            })
        else {
            showNoInternet()
        }
    }

    private fun getTodayMatchSuccessful(matchResponse: MatchResponse) {
        if (matchResponse.matches.isNotEmpty()) {
            adapter.updateAdapter(matchResponse.matches)
        } else {
            binding.noFixture.visibility = View.VISIBLE
        }
    }

    private fun getTodayMatchFailed(message: String) {
        show(message, true)
    }

    private fun showNoInternet() {
        binding.fixturesRecyclerview.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    override fun showLoading() {
        binding.includeProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.includeProgressBar.visibility = View.GONE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.noInternet.visibility = View.GONE
            getTodayMatch(getCurrentDate())
        }
    }

    companion object {
        fun newInstance() = TodayFixturesFragment()
    }

}
