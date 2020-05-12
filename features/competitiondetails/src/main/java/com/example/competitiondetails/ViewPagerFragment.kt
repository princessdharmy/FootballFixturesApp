package com.example.competitiondetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.common.base.BaseFragment
import com.example.competitiondetails.databinding.FragmentViewPagerBinding
import com.example.competitiondetails.tableFragment.TableFragment


class ViewPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPageAdapter: ViewPageAdapter

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

    private fun initAdapter(){
        viewPageAdapter = ViewPageAdapter(baseActivity.supportFragmentManager)
        viewPageAdapter.addFragment(TableFragment.newInstance().apply {
            arguments = Bundle().apply {
                //putLong("id", competitionId ?: 0L)
            }
        }, "Table")
    }

    private fun getIntents() {
        //val args:  by navArgs()
    }
    companion object {
        fun newInstance() = ViewPagerFragment()
    }
}
