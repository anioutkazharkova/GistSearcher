package com.azharkova.gistsearcher.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.view.viewholders.CommonGistInfoViewHolder
import com.azharkova.gistsearcher.view.viewholders.GistListViewHolder
import com.azharkova.gistsearcher.view.viewholders.SimpleGistViewHolder

class GistListAdapter(val onItemClick: ((Int) -> Unit))  :RecyclerView.Adapter<CommonGistInfoViewHolder>() {
    private var items: List<GistInfo>? = null
    var simplified: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(items: List<GistInfo>?) {
        this.items = items
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder:CommonGistInfoViewHolder, position: Int) {
        items?.get(position)?.let {
            holder.tag = position
            holder.bindItem(it)
        }
    }
    fun onItemViewClick(position: Int) {
        onItemClick(position)
    }
    override fun getItemCount() = items?.size ?: 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (simplified)
        {
           SimpleGistViewHolder(LayoutInflater.from(parent.context), parent) { onItemViewClick(it) }
        }else {
            GistListViewHolder(LayoutInflater.from(parent.context), parent) { onItemViewClick(it) }
        }
}