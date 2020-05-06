package com.example.competitiondetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.common.base.BaseFragment
import com.example.competitiondetails.databinding.FragmentViewPagerBinding


class ViewPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance() = ViewPagerFragment()
    }
}
