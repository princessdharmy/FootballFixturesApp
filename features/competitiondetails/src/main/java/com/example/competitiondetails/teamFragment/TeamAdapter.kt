package com.example.competitiondetails.teamFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.TeamListBinding
import com.example.presentation.models.Team

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