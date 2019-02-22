package com.azharkova.gistsearcher.view.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistInfo

class SimpleGistViewHolder (inflater: LayoutInflater,
                            container: ViewGroup,
                            private val onItemClick: ((Int) -> Unit)):
    CommonGistInfoViewHolder(inflater.inflate(R.layout.item_simple_gist, container, false)) {

    @BindView(R.id.text_title)
    protected lateinit var gistTitle: TextView


    override var tag: Int = 0

    init {
        ButterKnife.bind(this, itemView)
    }

   override fun bindItem(detail: GistInfo) {
        gistTitle.text = if  (detail.description.isNullOrEmpty()) detail.url else detail.description
    }

    @OnClick
    protected fun onDetailClick() {
        onItemClick(tag)
    }
}