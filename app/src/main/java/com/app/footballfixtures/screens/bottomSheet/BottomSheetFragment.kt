package com.app.footballfixtures.screens.bottomSheet

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.footballfixtures.databinding.FragmentBottomSheetBinding
import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Player
import com.app.footballfixtures.core.data.models.PlayerResponse
import com.app.footballfixtures.utils.glide.GlideApp
import com.app.footballfixtures.utils.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.reactivex.disposables.CompositeDisposable

class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(players: PlayerResponse) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString("name", players.name)
                putString("url", players.crestUrl)
                putParcelableArrayList("players", ArrayList(players.squad))
            }
        }
    }

    lateinit var binding: FragmentBottomSheetBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BottomSheetAdapter
    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>
    private val disposable = CompositeDisposable()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.click = MyHandler()
        if (arguments != null) {
            binding.clubName.text = arguments?.getString("name")
            if (arguments?.getString("url")?.endsWith(".svg") == true)
                GlideApp.with(this)
                    .`as`(PictureDrawable::class.java)
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(SvgSoftwareLayerSetter())
                    .load(Uri.parse(arguments?.getString("url")))
                    .into(binding.imageView3)
            else
                GlideApp.with(this)
                    .load(arguments?.getString("url"))
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageView3)
            initRecyclerView(arguments?.getParcelableArrayList("players") ?: ArrayList())
        }
    }

    fun updateContent(data: PlayerResponse) {
        if (activity != null) {
            binding.clubName.text = data.name
            if (data.crestUrl.endsWith(".svg"))
                GlideApp.with(this)
                    .`as`(PictureDrawable::class.java)
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(SvgSoftwareLayerSetter())
                    .load(Uri.parse(data.crestUrl))
                    .into(binding.imageView3)
            else
                GlideApp.with(this)
                    .load(data.crestUrl)
                    .placeholder(R.drawable.soccer)
                    .error(R.drawable.soccer)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageView3)
            initRecyclerView(data.squad)
            dialog.show()
        }
    }

    private fun initRecyclerView(players: List<Player>) {
        adapter = BottomSheetAdapter(players)
        recyclerView = binding.squadRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    inner class MyHandler {
        fun onClose(view: View) {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            dialog.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

}
