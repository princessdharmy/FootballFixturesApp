package com.example.competitions.competitions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.common.base.BaseFragment
import com.example.competitions.R
import com.example.competitions.databinding.CompetitionsFragmentBinding
import com.example.competitions.di.DaggerCompetitionComponent
import com.example.core.coreComponent
import com.example.presentation.models.Competitions
import com.example.presentation.models.Resource
import com.example.presentation.utils.Utilities.hasInternetConnection
import com.example.presentation.viewmodels.CompetitionsViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CompetitionsFragment : BaseFragment() {

    lateinit var binding: CompetitionsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CompetitionsAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionsViewModel by viewModels { factory }
    var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.competitions_fragment, container, false)
        val view = binding.root
        binding.click = MyHandler()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCompetitions()
    }

    private fun initRecyclerView() {
        adapter = CompetitionsAdapter(ArrayList(), clickListener)
        recyclerView = binding.competitionsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun getCompetitions() {
        disposable = hasInternetConnection().doOnSuccess {
            if (it)
                viewModel.getAllCompetitions().observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Resource.Status.LOADING -> {
                            println("Loading")
                        }
                        Resource.Status.ERROR -> {
                            println("Error")
                        }
                        Resource.Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data.competitions.isNotEmpty()) {
                                    binding.progressBar.visibility = View.GONE
                                    binding.noInternet.visibility = View.GONE
                                    binding.competitionsRecyclerview.visibility = View.VISIBLE
                                    adapter.updateAdapter(data.competitions)
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
        binding.competitionsRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    private val clickListener = View.OnClickListener {
        val competitions = it.tag as Competitions
        show("${competitions.id} selected", true)
        val action =
            CompetitionsFragmentDirections.actionCompetitionsFragmentToCompetitionDetailsFragment(
                competitions
            )
        it.findNavController().navigate(action)
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

    companion object {
        fun newInstance() = CompetitionsFragment()
    }

}
