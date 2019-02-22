package com.azharkova.gistsearcher.view.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.util.extensions.load
import de.hdodenhof.circleimageview.CircleImageView

class GistListViewHolder(inflater: LayoutInflater,
container: ViewGroup,
private val onItemClick: ((Int) -> Unit)):
CommonGistInfoViewHolder(inflater.inflate(R.layout.item_gist_list, container, false)) {

    @BindView(R.id.text_title)
    protected lateinit var gistTitle: TextView

    @BindView(R.id.text_user)
    protected lateinit var userText: TextView

    @BindView(R.id.avatar_image)
    protected lateinit var avatarImage:CircleImageView

   override var tag: Int = 0

    init {
        ButterKnife.bind(this, itemView)
    }

   override fun bindItem(detail: GistInfo) {
       gistTitle.text = if  (detail.description.isNullOrEmpty()) detail.url else detail.description
        userText.text = detail.owner.login
        detail.owner.avatarUrl?.let {
            avatarImage.load(it)
        }

    }

    @OnClick
    protected fun onDetailClick() {
        onItemClick(tag)
    }
}

open class CommonGistInfoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    open var tag: Int = 0
    open fun bindItem(detail: GistInfo){}
}