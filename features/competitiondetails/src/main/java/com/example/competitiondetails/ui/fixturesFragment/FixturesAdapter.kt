package com.example.competitiondetails.ui.fixturesFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.FixturesListItemBinding
import com.example.presentation.models.Match

class FixturesAdapter(
    private var singleMatchList: List<Match>
): RecyclerView.Adapter<FixturesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.fixtures_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return singleMatchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         //holder.binding?.match = singleMatchList[position]
    }

    fun updateAdapter(value: List<Match>){
        singleMatchList = value
        return notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: FixturesListItemBinding? = DataBindingUtil.bind(view)
        init {
            view.tag = binding
        }
    }
}