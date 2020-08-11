package com.example.competitions.ui.today

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.competitions.R
import com.example.competitions.databinding.TodayFixturesFragmentBinding
import com.example.competitions.di.DaggerCompetitionComponent
import com.example.core.coreComponent
import com.example.presentation.models.Resource
import com.example.presentation.utils.Utilities.getCurrentDate
import com.example.presentation.viewmodels.CompetitionsViewModel

import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TodayFixturesFragment : BaseFragment() {

    lateinit var binding: TodayFixturesFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodayFixturesAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionsViewModel by viewModels { factory }
    private var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionComponent.factory().create(coreComponent()).inject(this)
    }

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
        getTodayMatch(getCurrentDate())
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
                viewModel.getAllMatches(date).observe(viewLifecycleOwner, Observer { result ->
                    when(result.status) {
                        Resource.Status.LOADING -> { println("Loading") }
                        Resource.Status.ERROR -> { println("Error") }
                        Resource.Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data.matches?.isNotEmpty()!!) {
                                    binding.progressBar.visibility = View.GONE
                                    binding.fixturesRecyclerview.visibility = View.VISIBLE
                                    adapter.updateAdapter(data.matches!!)
                                } else {
                                    binding.fixturesRecyclerview.visibility = View.GONE
                                    binding.progressBar.visibility = View.GONE
                                    binding.noFixture.visibility = View.VISIBLE
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
        binding.fixturesRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.progressBar.visibility = View.VISIBLE
            binding.noInternet.visibility = View.GONE
            getTodayMatch(getCurrentDate())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }

    companion object {
        fun newInstance() = TodayFixturesFragment()
    }

}
