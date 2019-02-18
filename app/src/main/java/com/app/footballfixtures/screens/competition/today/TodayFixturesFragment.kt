package com.app.footballfixtures.screens.competition.today

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
import com.app.footballfixtures.utils.Utilities.hasInternetConnection
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TodayFixturesFragment : DaggerFragment() {

    companion object {
        fun newInstance() = TodayFixturesFragment()
    }

    lateinit var binding: com.app.footballfixtures.databinding.TodayFixturesFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TodayFixturesAdapter
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: TodayFixturesViewModel
    var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.today_fixtures_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(TodayFixturesViewModel::class.java)

        getTodayMatch(Utilities.getCurrentDate())
    }

    private fun initRecyclerView() {
        adapter = TodayFixturesAdapter(ArrayList())
        recyclerView = binding.fixturesRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    /**
     * Fetch list of all matches for today
     */
    private fun getTodayMatch(date: String) {
        disposable = hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getMatches(date).observe(this, Observer { data ->
                    binding.progressBar.visibility = View.GONE
                    if (data != null && data.matches.isNotEmpty()) {
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

    fun showNoInternet() {
        binding.fixturesRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getTodayMatch(Utilities.getCurrentDate())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

}
