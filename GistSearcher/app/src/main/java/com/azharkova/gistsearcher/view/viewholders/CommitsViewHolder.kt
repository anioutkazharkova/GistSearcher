package com.azharkova.gistsearcher.view.viewholders

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistFile

class CommitsViewHolder(inflater: LayoutInflater,
                        container: ViewGroup,
                        private val onItemClick: ((Int) -> Unit)):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_commits_list, container, false)) {

    @BindView(R.id.text_commit_version)
    protected lateinit var commitsVersionText: TextView

    @BindView(R.id.text_deletions)
    protected lateinit var deletionsText: TextView

    @BindView(R.id.text_additions)
    protected lateinit var additionsText: TextView

    @BindView(R.id.text_total)
    protected lateinit var totalText: TextView

    var tag: Int = 0

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindItem(detail: GistCommitInfo) {
       commitsVersionText.text = detail.version
       deletionsText.text ="${detail.status.deletions}"
        additionsText.text ="${detail.status.additions}"
        totalText.text ="${detail.status.total}"
    }

    @OnClick
    protected fun onDetailClick() {
        onItemClick(tag)
    }
}

