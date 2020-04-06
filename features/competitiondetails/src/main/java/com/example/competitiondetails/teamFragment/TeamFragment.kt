package com.example.competitiondetails.teamFragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.presentation.utils.Utilities
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.TeamFragmentBinding
import com.example.presentation.models.PlayerResponse
import com.example.presentation.models.Team

import com.example.presentation.viewmodels.TeamViewModel
import io.reactivex.disposables.Disposable

class TeamFragment : BaseFragment() {

    companion object {
        fun newInstance() = TeamFragment()
        var competitionId: Long = 0L
    }

    lateinit var binding: TeamFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamAdapter
    private var listener: OnFragmentInteractionListener? = null
//    @Inject
//    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: TeamViewModel
    var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_fragment, container, false)
        val view = binding.root
        //binding.click = MyHandler()
        getIntents()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProviders.of(this, factory).get(TeamViewModel::class.java)
        getTeams(competitionId)
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener)
//            listener = context
//        else
//            throw IllegalArgumentException("${context.toString()} must implement OnFragmentInteractionListener")
//    }

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
//            if (it)
//                viewModel.getTeams(id).observe(this, Observer { data ->
//                    if (data != null && data.teams.isNotEmpty()) {
//                        binding.progressBar.visibility = View.GONE
//                        binding.teamRecyclerview.visibility = View.VISIBLE
//                        adapter.updateAdapter(data.teams)
//                    }
//                })
//            else {
//                showNoInternet()
//            }
        }.doOnError {
            showNoInternet()
        }.subscribe()
    }

    private fun getPlayers(id: Long) {
        binding.progressBar.visibility = View.VISIBLE
//        viewModel.getPlayers(id).observe(this, Observer {
//            binding.progressBar.visibility = View.GONE
//             if(it != null){
//                 listener?.sendTeam(it)
//             }
//         })
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
