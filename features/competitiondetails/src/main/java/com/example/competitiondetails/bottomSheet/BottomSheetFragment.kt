package com.example.competitiondetails.bottomSheet

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.FragmentBottomSheetBinding
import com.example.presentation.models.Player
import com.example.presentation.models.PlayerResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable

class BottomSheetFragment : BottomSheetDialogFragment() {

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
            val sheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
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
        //binding.click = MyHandler()
        if (arguments != null) {
            binding.clubName.text = arguments?.getString("name")
            if (arguments?.getString("url")?.endsWith(".svg") == true)
//                GlideApp.with(this)
//                    .`as`(PictureDrawable::class.java)
//                    .placeholder(R.drawable.soccer)
//                    .error(R.drawable.soccer)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .listener(SvgSoftwareLayerSetter())
//                    .load(Uri.parse(arguments?.getString("url")))
//                    .into(binding.imageView3)
            else
//                GlideApp.with(this)
//                    .load(arguments?.getString("url"))
//                    .placeholder(R.drawable.soccer)
//                    .error(R.drawable.soccer)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .into(binding.imageView3)
            initRecyclerView(arguments?.getParcelableArrayList("players") ?: ArrayList())
        }
    }

    fun updateContent(data: PlayerResponse) {
        if (activity != null) {
            binding.clubName.text = data.name
            if (data.crestUrl.endsWith(".svg"))
//                GlideApp.with(this)
//                    .`as`(PictureDrawable::class.java)
//                    .placeholder(R.drawable.soccer)
//                    .error(R.drawable.soccer)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .listener(SvgSoftwareLayerSetter())
//                    .load(Uri.parse(data.crestUrl))
//                    .into(binding.imageView3)
            else
//                GlideApp.with(this)
//                    .load(data.crestUrl)
//                    .placeholder(R.drawable.soccer)
//                    .error(R.drawable.soccer)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .into(binding.imageView3)
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

    companion object {
        fun newInstance(players: PlayerResponse) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString("name", players.name)
                putString("url", players.crestUrl)
                putParcelableArrayList("players", ArrayList(players.squad))
            }
        }
    }

}
