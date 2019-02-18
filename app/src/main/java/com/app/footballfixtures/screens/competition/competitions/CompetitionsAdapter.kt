package com.app.footballfixtures.screens.competition.competitions

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Competitions
import com.app.footballfixtures.databinding.CompetitionsListItemBinding
import com.app.footballfixtures.screens.competition.competitions.CompetitionsAdapter.ViewHolder

class CompetitionsAdapter(
    private var competitionsList: List<Competitions>,
    private val listener: View.OnClickListener
): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.competitions_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return competitionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.click = listener
        holder.binding?.competitions = competitionsList[position]
    }

    fun updateAdapter(list: List<Competitions>){
        competitionsList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: CompetitionsListItemBinding? = DataBindingUtil.bind(view)

        init {
            view.tag = binding
        }

    }
}