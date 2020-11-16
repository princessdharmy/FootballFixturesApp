package com.example.competitiondetails.ui.tableFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.TableFragmentBinding
import com.example.common.utils.network.NetworkStatus
import com.example.presentation.models.StandingResponse
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : BaseFragment() {

    lateinit var binding: TableFragmentBinding
    private lateinit var adapter: TableAdapter
    private val viewModel: CompetitionDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.table_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        getIntents()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStandings(competitionId)
    }

    private fun getIntents() {
        competitionId = arguments?.getLong("id")!!
    }

    private fun initRecyclerView() {
        adapter = TableAdapter(ArrayList())
        binding.tableRecyclerview.adapter = adapter
        binding.tableRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.tableRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun getStandings(id: Long) {
        viewModel.getStandings(id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkStatus.Loading -> {
                    showLoading()
                }
                is NetworkStatus.Error -> {
                    hideLoading()
                    getStandingsFailed(result.errorMessage!!)
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let { getStandingsSuccessful(it) }
                }
            }
        })
    }

    private fun getStandingsSuccessful(standingResponse: StandingResponse) {
        if (standingResponse.standings.isNullOrEmpty()) {
            binding.noData.visibility = View.VISIBLE
        } else {
            binding.tableRecyclerview.visibility = View.VISIBLE
            standingResponse.standings[0].table.let { adapter.updateAdapter(it) }
        }
    }

    private fun getStandingsFailed(message: String) {
        show(message, true)
        showRetryMessage()
    }

    private fun showRetryMessage() {
        binding.tableRecyclerview.visibility = View.GONE
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
            getStandings(competitionId)
        }
    }

    companion object {
        fun newInstance() = TableFragment()
        var competitionId: Long = 0L
    }
}
