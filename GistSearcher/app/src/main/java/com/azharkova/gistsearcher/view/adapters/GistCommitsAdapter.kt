package com.azharkova.gistsearcher.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.view.viewholders.CommitsViewHolder

class GistCommitsAdapter (val onItemClick: ((Int) -> Unit))  : RecyclerView.Adapter<CommitsViewHolder>() {
    private var items: List<GistCommitInfo>? = null


    fun setData(items: List<GistCommitInfo>?) {
        this.items = items
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
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
        CommitsViewHolder(LayoutInflater.from(parent.context), parent){onItemViewClick(it)}
}


