package com.azharkova.gistsearcher.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.azharkova.gistsearcher.data.GistFile
import com.azharkova.gistsearcher.view.viewholders.GistFileViewHolder
import com.azharkova.gistsearcher.view.viewholders.GistListViewHolder

class GistFileAdapter (val onItemClick: ((Int) -> Unit))  : RecyclerView.Adapter<GistFileViewHolder>() {
    private var items: List<GistFile>? = null

    fun setData(items: List<GistFile>?) {
        this.items = items
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: GistFileViewHolder, position: Int) {
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
        GistFileViewHolder(LayoutInflater.from(parent.context), parent){onItemViewClick(it)}
}