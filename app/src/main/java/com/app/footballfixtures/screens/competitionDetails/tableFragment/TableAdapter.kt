package com.app.footballfixtures.screens.competitionDetails.tableFragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.footballfixtures.R
import com.app.footballfixtures.core.data.models.Table
import com.app.footballfixtures.databinding.TableListBinding

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