package com.app.footballfixtures.screens.competitionDetails.teamFragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Player
import com.app.footballfixtures.core.data.models.PlayerResponse
import com.app.footballfixtures.core.data.models.Team
import com.app.footballfixtures.screens.bottomSheet.BottomSheetFragment
import com.app.footballfixtures.utils.Utilities
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TeamFragment : DaggerFragment() {

    companion object {
        fun newInstance() = TeamFragment()
        var competitionId: Long = 0L
    }

    lateinit var binding: com.app.footballfixtures.databinding.TeamFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamAdapter
    private var listener: OnFragmentInteractionListener? = null
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: TeamViewModel
    var disposable: Disposable? = null

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
        viewModel = ViewModelProviders.of(this, factory).get(TeamViewModel::class.java)
        getTeams(competitionId)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener)
            listener = context
        else
            throw IllegalArgumentException("${context.toString()} must implement OnFragmentInteractionListener")
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
        disposable = Utilities.hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getTeams(id).observe(this, Observer { data ->
                    if (data != null && data.teams.isNotEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        binding.teamRecyclerview.visibility = View.VISIBLE
                        adapter.updateAdapter(data.teams)
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
        viewModel.getPlayers(id).observe(this, Observer {
            binding.progressBar.visibility = View.GONE
             if(it != null){
                 listener?.sendTeam(it)
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
        getPlayers(team.id)
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getTeams(competitionId)
        }
    }

    interface OnFragmentInteractionListener {
        fun sendTeam(playerResponse: PlayerResponse)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

}
