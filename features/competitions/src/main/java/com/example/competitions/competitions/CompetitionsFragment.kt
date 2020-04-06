package com.example.competitions.competitions

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
import com.example.competitions.R
import com.example.competitions.databinding.CompetitionsFragmentBinding
import com.example.presentation.models.Competitions
import com.example.presentation.viewmodels.CompetitionsViewModel
import io.reactivex.disposables.Disposable

class CompetitionsFragment : BaseFragment() {

    companion object {
        fun newInstance() = CompetitionsFragment()
    }

    lateinit var binding: CompetitionsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CompetitionsAdapter
//    @Inject
//    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: CompetitionsViewModel
    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.competitions_fragment, container, false)
        val view = binding.root
        //binding.click = MyHandler()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProviders.of(this, factory).get(CompetitionsViewModel::class.java)
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
//            viewModel.getCompetitions().observe(this, Observer { data ->
//                if (data?.competitions?.isNotEmpty() == true) {
//                    binding.progressBar.visibility = View.GONE
//                    binding.noInternet.visibility = View.GONE
//                    binding.competitionsRecyclerview.visibility = View.VISIBLE
//                    adapter.updateAdapter(data.competitions)
//                } else {
//                    showNoInternet()
//                }
//            })
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
//        val intent = Intent(context, CompetitionDetailsActivity::class.java)
//        intent.putExtra("id", competitions.id)
//        intent.putExtra("name", competitions.name)
//        activity?.startActivity(intent)
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
