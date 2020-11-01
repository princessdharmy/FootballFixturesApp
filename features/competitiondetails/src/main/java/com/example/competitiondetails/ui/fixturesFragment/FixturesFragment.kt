package com.example.competitiondetails.ui.fixturesFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.FixturesFragmentBinding
import com.example.competitiondetails.di.DaggerCompetitionDetailsComponent
import com.example.core.coreComponent
import com.example.common.utils.network.NetworkStatus
import com.example.presentation.models.MatchResponse
import com.example.presentation.utils.Utilities.getCurrentDate
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import javax.inject.Inject

class FixturesFragment : BaseFragment() {

    lateinit var binding: FixturesFragmentBinding
    private lateinit var adapter: FixturesAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionDetailsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionDetailsComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fixtures_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        getIntents()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSingleMatch(competitionId, getCurrentDate())
    }

    private fun getIntents() {
        competitionId = arguments?.getLong("id")!!
    }

    private fun initRecyclerView() {
        adapter = FixturesAdapter(ArrayList())
        binding.fixturesRecyclerview.adapter = adapter
        binding.fixturesRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.fixturesRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun getSingleMatch(id: Long, date: String) {
        viewModel.getSingleMatch(id, date).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkStatus.Loading -> {
                    showLoading()
                }
                is NetworkStatus.Error -> {
                    hideLoading()
                    getSingleMatchFailed(result.errorMessage!!)
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let { getSingleMatchSuccessful(it) }
                }
            }
        })
    }

    private fun getSingleMatchSuccessful(matchResponse: MatchResponse) {
        if (matchResponse.matches.isNullOrEmpty()) {
            binding.fixturesRecyclerview.visibility = View.GONE
            binding.noFixture.visibility = View.VISIBLE
        } else {
            binding.fixturesRecyclerview.visibility = View.VISIBLE
            adapter.updateAdapter(matchResponse.matches)
        }
    }

    private fun getSingleMatchFailed(message: String) {
        show(message, true)
        showRetryMessage()
    }

    private fun showRetryMessage() {
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
            getSingleMatch(competitionId, getCurrentDate())
        }
    }

    companion object {
        fun newInstance() = FixturesFragment()
        var competitionId: Long = 0L
    }

}
