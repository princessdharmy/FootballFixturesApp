package com.app.footballfixtures.screens.competitionDetails.teamFragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Team
import com.app.footballfixtures.databinding.TeamListBinding

class TeamAdapter(
    private var teamList: List<Team>,
    private val listener: View.OnClickListener
): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.team_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.click = listener
        holder.binding?.team = teamList[position]
    }

    fun updateAdapter(value: List<Team>){
        teamList = value
        return notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: TeamListBinding? = DataBindingUtil.bind(view)
        init {
            view.tag = binding
        }
    }
}