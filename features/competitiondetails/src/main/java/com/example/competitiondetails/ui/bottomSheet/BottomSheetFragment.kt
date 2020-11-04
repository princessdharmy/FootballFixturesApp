package com.example.competitiondetails.ui.bottomSheet

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.glide.GlideApp
import com.example.common.utils.glide.SvgSoftwareLayerSetter
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.FragmentBottomSheetBinding
import com.example.presentation.models.PlayerResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.competitiondetails.di.DaggerCompetitionDetailsComponent
import com.example.core.coreComponent
import com.example.common.utils.network.NetworkStatus
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import javax.inject.Inject

class BottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CompetitionDetailsViewModel by viewModels { factory }

    lateinit var binding: FragmentBottomSheetBinding
    private lateinit var adapter: BottomSheetAdapter
    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>
    private var teamId: Long = 0L

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCompetitionDetailsComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        val view = binding.root
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.click = MyHandler()
        getIntents()
    }

    private fun initRecyclerView() {
        adapter = BottomSheetAdapter()
        binding.squadRecyclerview.adapter = adapter
        binding.squadRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.squadRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun getIntents() {
        teamId = arguments?.getLong("id")!!
        getPlayers(teamId)
    }

    private fun getPlayers(id: Long) {
        viewModel.getPlayers(id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkStatus.Loading -> binding.includeProgressBar.visibility = View.VISIBLE
                is NetworkStatus.Error -> {
                    binding.includeProgressBar.visibility = View.GONE
                    getPlayersFailed(result.errorMessage!!)
                }
                is NetworkStatus.Success -> {
                    binding.includeProgressBar.visibility = View.GONE
                    result.data?.let { getPlayersSuccessful(it) }
                }
            }
        })
    }

    private fun showContent(data: PlayerResponse) {
        if (activity != null) {
            binding.clubName.text = data.name
            if (data.crestUrl?.endsWith(".svg")!!)
                GlideApp.with(this)
                    .`as`(PictureDrawable::class.java)
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(withCrossFade())
                    .listener(SvgSoftwareLayerSetter())
                    .load(Uri.parse(data.crestUrl))
                    .into(binding.imageView3)
            else
                GlideApp.with(this)
                    .load(data.crestUrl)
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(withCrossFade())
                    .into(binding.imageView3)

            data.squad.let { adapter.updateAdapter(it) }
            dialog.show()
        }
    }

    inner class MyHandler {
        fun onClose(view: View) {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            dialog.hide()
        }

        fun onTapToRetry(view: View) {
            binding.noInternet.visibility = View.GONE
            getPlayers(teamId)
        }
    }

    private fun showRetryMessage() {
        binding.squadRecyclerview.visibility = View.GONE
        binding.noInternet.visibility = View.VISIBLE
    }

    private fun getPlayersSuccessful(playerResponse: PlayerResponse) {
        if (playerResponse.squad.isNullOrEmpty()) {
            binding.noData.visibility = View.VISIBLE
        } else {
            binding.squadRecyclerview.visibility = View.VISIBLE
            showContent(playerResponse)
        }
    }

    private fun getPlayersFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        showRetryMessage()
    }

    companion object {
        const val TAG = "Bottom_Sheet"

        fun newInstance(id: Long) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putLong("id", id)
            }
        }
    }

}
