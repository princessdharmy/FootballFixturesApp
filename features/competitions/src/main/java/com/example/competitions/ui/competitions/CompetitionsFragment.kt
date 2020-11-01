package com.example.competitions.ui.competitions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.base.BaseFragment
import com.example.competitions.R
import com.example.competitions.databinding.CompetitionsFragmentBinding
import com.example.competitions.di.DaggerCompetitionComponent
import com.example.core.coreComponent
import com.example.presentation.models.Competitions
import com.example.presentation.viewmodels.CompetitionsViewModel
import javax.inject.Inject

class CompetitionsFragment : BaseFragment() {

    lateinit var binding: CompetitionsFragmentBinding
    private lateinit var adapter: CompetitionsAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionsViewModel by viewModels { factory }

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
        binding.competitionsRecyclerview.adapter = adapter
        binding.competitionsRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.competitionsRecyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun getCompetitions() {
        viewModel.getAllCompetitions().observe(viewLifecycleOwner, Observer { result ->
            if (result?.isNotEmpty()!!) {
                adapter.updateAdapter(result)
            }
        })
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    private val clickListener = View.OnClickListener {
        val competitions = it.tag as Competitions
        val action =
            CompetitionsFragmentDirections.actionCompetitionsFragmentToCompetitionDetailsFragment(
                competitions
            )
        it.findNavController().navigate(action)
    }

    inner class MyHandler {
        fun onTapToRetry(view: View) {
            binding.noInternet.visibility = View.GONE
            getCompetitions()
        }
    }

    companion object {
        fun newInstance() = CompetitionsFragment()
    }

}
