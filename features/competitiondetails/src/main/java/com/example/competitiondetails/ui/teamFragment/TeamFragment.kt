package com.example.competitiondetails.ui.teamFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.common.base.BaseFragment
import com.example.competitiondetails.R
import com.example.competitiondetails.ui.bottomSheet.BottomSheetFragment
import com.example.competitiondetails.databinding.TeamFragmentBinding
import com.example.common.utils.network.NetworkStatus
import com.example.presentation.models.Team
import com.example.presentation.models.TeamResponse
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamFragment : BaseFragment() {

    lateinit var binding: TeamFragmentBinding
    private lateinit var adapter: TeamAdapter

//    @Inject
//    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionDetailsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        getIntents()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTeams(competitionId)
    }

    private fun getIntents() {
        competitionId = arguments?.getLong("id")!!
    }

    private fun initRecyclerView() {
        adapter = TeamAdapter(ArrayList(), clickListener)
        binding.teamRecyclerview.adapter = adapter
        binding.teamRecyclerview.layoutManager = GridLayoutManager(context, 3)
    }

    private fun getTeams(id: Long) {
        viewModel.getTeams(id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkStatus.Loading -> {
                    showLoading()
                }
                is NetworkStatus.Error -> {
                    hideLoading()
                    getTeamsFailed(result.errorMessage!!)
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let { getTeamsSuccessful(it) }
                }
            }
        })
    }

    private fun getTeamsSuccessful(teamResponse: TeamResponse) {
        if (teamResponse.teams.isNullOrEmpty()) {
            binding.noData.visibility = View.VISIBLE
        } else {
            binding.teamRecyclerview.visibility = View.VISIBLE
            teamResponse.let { adapter.updateAdapter(it.teams) }
        }
    }

    private fun getTeamsFailed(message: String) {
        show(message, true)
        showRetryMessage()
    }

    private fun showRetryMessage() {
        binding.teamRecyclerview.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    private val clickListener = View.OnClickListener {
        val team = it.tag as Team
        openBottomSheet(team.id)
    }

    private fun openBottomSheet(teamId: Long) {
        val transaction = baseActivity.supportFragmentManager.beginTransaction()
        val previous =
            baseActivity.supportFragmentManager.findFragmentByTag(BottomSheetFragment.TAG)
        if (previous != null) transaction.remove(previous)
        transaction.addToBackStack(null)

        val dialogFragment = BottomSheetFragment.newInstance(teamId)
        dialogFragment.show(transaction, BottomSheetFragment.TAG)
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
            getTeams(competitionId)
        }
    }

    companion object {
        fun newInstance() = TeamFragment()
        var competitionId: Long = 0L
    }

}
