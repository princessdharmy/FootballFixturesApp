package com.app.footballfixtures.screens.competition.competitions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Competitions
import com.app.footballfixtures.databinding.CompetitionsFragmentBinding
import com.app.footballfixtures.screens.competitionDetails.CompetitionDetailsActivity
import com.app.footballfixtures.utils.Utilities
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CompetitionsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = CompetitionsFragment()
    }

    lateinit var binding: CompetitionsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CompetitionsAdapter
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: CompetitionsViewModel
    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.competitions_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(CompetitionsViewModel::class.java)
        getCompetitions()
    }

    private fun initRecyclerView() {
        adapter = CompetitionsAdapter(ArrayList(), clickListener)
        recyclerView = binding.competitionsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun getCompetitions() {
        disposable = Utilities.hasInternetConnection().doOnSuccess {
            viewModel.getCompetitions().observe(this, Observer { data ->
                if (data?.competitions?.isNotEmpty() == true) {
                    binding.progressBar.visibility = View.GONE
                    binding.noInternet.visibility = View.GONE
                    binding.competitionsRecyclerview.visibility = View.VISIBLE
                    adapter.updateAdapter(data.competitions)
                } else {
                    showNoInternet()
                }
            })
        }.doOnError {
            showNoInternet()
        }.subscribe()
    }

    private fun showNoInternet() {
        binding.competitionsRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    private val clickListener = View.OnClickListener {
        val competitions = it.tag as Competitions
        val intent = Intent(context, CompetitionDetailsActivity::class.java)
        intent.putExtra("id", competitions.id)
        intent.putExtra("name", competitions.name)
        activity?.startActivity(intent)
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getCompetitions()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

}
