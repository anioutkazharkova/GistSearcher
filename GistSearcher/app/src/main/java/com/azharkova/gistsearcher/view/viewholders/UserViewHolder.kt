package com.azharkova.gistsearcher.view.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.util.extensions.load
import de.hdodenhof.circleimageview.CircleImageView

class UserViewHolder (inflater: LayoutInflater,
                      container: ViewGroup,
                      private val onItemClick: ((Int) -> Unit)):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user_layout, container, false)) {


    @BindView(R.id.avatar_image)
    protected lateinit var avatarImage: CircleImageView

    var tag: Int = 0

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindItem(detail:GistUser) {
        detail.avatarUrl?.let {
            avatarImage.load(it)
        }

    }

    @OnClick
    protected fun onDetailClick() {
        onItemClick(tag)
    }
}