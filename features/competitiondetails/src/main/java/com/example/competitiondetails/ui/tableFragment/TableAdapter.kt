package com.example.competitiondetails.ui.tableFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.competitiondetails.R
import com.example.competitiondetails.databinding.TableListBinding
import com.example.presentation.models.Table

class TableAdapter(
    private var tableList: List<Table>
): RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.table_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tableList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.tables = tableList[position]
    }

    fun updateAdapter(value: List<Table>){
        tableList = value
        return notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: TableListBinding? = DataBindingUtil.bind(view)
        init {
            view.tag = binding
        }
    }
}