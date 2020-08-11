package com.example.competitiondetails.ui.tableFragment

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
import com.example.competitiondetails.databinding.TableFragmentBinding
import com.example.competitiondetails.di.DaggerCompetitionDetailsComponent
import com.example.core.coreComponent
import com.example.presentation.models.Resource
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TableFragment : BaseFragment() {

    lateinit var binding: TableFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TableAdapter
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
        recyclerView = binding.tableRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun getStandings(id: Long) {
        disposable = hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getStandings(id).observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Resource.Status.LOADING -> {
                            println("Loading")
                        }
                        Resource.Status.ERROR -> {
                            println("Error")
                        }
                        Resource.Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data.standings.isNullOrEmpty()) {
                                    binding.progressBar.visibility = View.GONE
                                    binding.noData.visibility = View.VISIBLE
                                }
                                else {
                                    binding.progressBar.visibility = View.GONE
                                    binding.tableRecyclerview.visibility = View.VISIBLE
                                    data.standings!![0].table?.let { it1 -> adapter.updateAdapter(it1) }
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

    private fun showNoInternet() {
        binding.tableRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getStandings(competitionId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

    companion object {
        fun newInstance() = TableFragment()
        var competitionId: Long = 0L
    }
}
