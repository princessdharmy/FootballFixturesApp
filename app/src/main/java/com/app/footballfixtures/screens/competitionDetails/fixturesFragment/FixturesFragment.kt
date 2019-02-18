package com.app.footballfixtures.screens.competitionDetails.fixturesFragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
import com.app.footballfixtures.utils.Utilities
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class FixturesFragment : DaggerFragment() {

    companion object {
        fun newInstance() = FixturesFragment()
        val TAG: String = FixturesFragment::class.java.simpleName
        var competitionId: Long = 0L
    }

    lateinit var binding: com.app.footballfixtures.databinding.FixturesFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FixturesAdapter
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: FixturesViewModel
    var disposable: Disposable? = null

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
        viewModel = ViewModelProviders.of(this, factory).get(FixturesViewModel::class.java)

        getSingleMatch(competitionId, Utilities.getCurrentDate())
    }

    private fun getIntents() {
        competitionId = arguments?.getLong("id")!!
    }

    private fun initRecyclerView() {
        adapter = FixturesAdapter(ArrayList())
        recyclerView = binding.fixturesRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun getSingleMatch(id: Long, date: String) {
        disposable = Utilities.hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getSingleMatch(id, date).observe(this, Observer { data ->
                    if (data != null && data.matches.isNotEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        binding.fixturesRecyclerview.visibility = View.VISIBLE
                        adapter.updateAdapter(data.matches)
                    } else {
                        binding.fixturesRecyclerview.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.noFixture.visibility = View.VISIBLE
                    }
                })
            else {
                showNoInternet()
            }
        }.doOnError {
            showNoInternet()
        }.subscribe()
    }

    private fun showNoInternet() {
        binding.fixturesRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getSingleMatch(competitionId, Utilities.getCurrentDate())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

}
