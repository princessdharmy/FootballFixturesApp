package com.example.competitiondetails.teamFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.presentation.utils.Utilities
import com.example.competitiondetails.R
import com.example.competitiondetails.bottomSheet.BottomSheetFragment
import com.example.competitiondetails.databinding.TeamFragmentBinding
import com.example.competitiondetails.di.DaggerCompetitionDetailsComponent
import com.example.core.coreComponent
import com.example.presentation.models.PlayerResponse
import com.example.presentation.models.Resource
import com.example.presentation.models.Team
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.presentation.viewmodels.CompetitionDetailsViewModel

import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TeamFragment : BaseFragment() {

    lateinit var binding: TeamFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionDetailsViewModel by viewModels { factory }
    var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionDetailsComponent.factory().create(coreComponent()).inject(this)
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
        recyclerView = binding.teamRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
    }

    private fun getTeams(id: Long) {
        disposable = hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getTeams(id).observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Resource.Status.LOADING -> {
                            println("Loading")
                        }
                        Resource.Status.ERROR -> {
                            println("Error")
                        }
                        Resource.Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data.teams.isNullOrEmpty()) {
                                    binding.progressBar.visibility = View.GONE
                                    binding.noData.visibility = View.VISIBLE
                                } else {
                                    binding.progressBar.visibility = View.GONE
                                    binding.teamRecyclerview.visibility = View.VISIBLE
                                    adapter.updateAdapter(data.teams)
                                }
                            }
                        }
                    }
                })
            else {
                showNoInternet()
            }
        }.doOnError {
            showNoInternet()
        }.subscribe()
    }

    private fun getPlayers(id: Long) {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPlayers(id).observe(viewLifecycleOwner, Observer { result ->
            binding.progressBar.visibility = View.GONE
            when (result.status) {
                Resource.Status.LOADING -> {
                    println("Loading")
                }
                Resource.Status.ERROR -> {
                    println("Error")
                }
                Resource.Status.SUCCESS -> {
                    result.data?.let { data ->
                        //if (data.squad.isNotEmpty()) {
                            Log.e("TAG", "${data.squad}")
                            //showContent(data)
                       // }
                    }
                }
            }
        })
    }

    private fun showNoInternet() {
        binding.teamRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    private val clickListener = View.OnClickListener {
        val team = it.tag as Team
        //getPlayers(team.id)
        openBottomSheet(team.id)
    }

    private fun openBottomSheet(teamId: Long){
        val transaction = baseActivity.supportFragmentManager.beginTransaction()
        val previous = baseActivity.supportFragmentManager.findFragmentByTag(BottomSheetFragment.TAG)
        if (previous != null) transaction.remove(previous)
        transaction.addToBackStack(null)

        val dialogFragment = BottomSheetFragment.newInstance(teamId)
        dialogFragment.show(transaction, BottomSheetFragment.TAG)
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getTeams(competitionId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

    companion object {
        fun newInstance() = TeamFragment()
        var competitionId: Long = 0L
    }

}
