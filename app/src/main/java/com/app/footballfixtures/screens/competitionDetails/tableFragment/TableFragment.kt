package com.app.footballfixtures.screens.competitionDetails.tableFragment

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

class TableFragment : DaggerFragment() {

    companion object {
        fun newInstance() = TableFragment()
        var competitionId: Long = 0L
    }

    lateinit var binding: com.app.footballfixtures.databinding.TableFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TableAdapter
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: TableViewModel
    var disposable: Disposable? = null

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
        viewModel = ViewModelProviders.of(this, factory).get(TableViewModel::class.java)

        getStandings(competitionId, "TOTAL")
    }

    private fun getIntents() {
        competitionId = arguments?.getLong("id")!!
    }

    private fun initRecyclerView() {
        adapter = TableAdapter(ArrayList())
        recyclerView = binding.tableRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun getStandings(id: Long, standingType: String) {
        disposable = Utilities.hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getStandings(id, standingType).observe(this, Observer { data ->
                    if (data != null && data.standings.isNotEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        binding.tableRecyclerview.visibility = View.VISIBLE
                        adapter.updateAdapter(data.standings[0].table)
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
        binding.tableRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getStandings(competitionId, "TOTAL")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

}
