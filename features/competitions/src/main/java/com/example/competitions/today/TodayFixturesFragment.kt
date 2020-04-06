package com.example.competitions.today

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.presentation.utils.Utilities
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.competitions.R
import com.example.competitions.databinding.TodayFixturesFragmentBinding

import io.reactivex.disposables.Disposable

class TodayFixturesFragment : BaseFragment() {

    companion object {
        fun newInstance() = TodayFixturesFragment()
    }

    lateinit var binding: TodayFixturesFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TodayFixturesAdapter
//    @Inject
//    internal lateinit var factory: ViewModelProvider.Factory
    //private lateinit var viewModel: TodayFixturesViewModel
    var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.today_fixtures_fragment, container, false)
        val view = binding.root
        //binding.click = MyHandler()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProviders.of(this, factory).get(TodayFixturesViewModel::class.java)

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
//            if (it)
//                viewModel.getMatches(date).observe(this, Observer { data ->
//                    binding.progressBar.visibility = View.GONE
//                    if (data != null && data.matches.isNotEmpty()) {
//                        binding.fixturesRecyclerview.visibility = View.VISIBLE
//                        adapter.updateAdapter(data.matches)
//                    } else {
//                        binding.fixturesRecyclerview.visibility = View.GONE
//                        binding.progressBar.visibility = View.GONE
//                        binding.noFixture.visibility = View.VISIBLE
//                    }
//                })
//            else {
//                showNoInternet()
//            }
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
