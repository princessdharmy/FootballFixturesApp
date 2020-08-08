package com.example.competitions.ui.competitions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.competitions.R
import com.example.competitions.ui.competitions.CompetitionsAdapter.ViewHolder
import com.example.competitions.databinding.CompetitionsListItemBinding
import com.example.presentation.models.Competitions

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