package com.azharkova.gistsearcher.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.view.viewholders.UserViewHolder

class UsersAdapter (val onItemClick: ((Int) -> Unit))  : RecyclerView.Adapter<UserViewHolder>() {
    private var items: List<GistUser>? = null

    fun setData(items: List<GistUser>?) {
        this.items = items
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
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
        UserViewHolder(LayoutInflater.from(parent.context), parent){onItemViewClick(it)}
}