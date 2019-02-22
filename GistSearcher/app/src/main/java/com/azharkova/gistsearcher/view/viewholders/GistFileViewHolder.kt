package com.azharkova.gistsearcher.view.viewholders

import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistFile
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.util.extensions.load
import de.hdodenhof.circleimageview.CircleImageView

class GistFileViewHolder(inflater: LayoutInflater,
                         container: ViewGroup,
                         private val onItemClick: ((Int) -> Unit)):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_file_layout, container, false)) {

    @BindView(R.id.text_filename)
    protected lateinit var filenameText: TextView

    @BindView(R.id.text_fileurl)
    protected lateinit var fileurlText: TextView


    var tag: Int = 0

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindItem(detail: GistFile) {
       filenameText.text =detail.filename
        fileurlText.text = detail.url
        fileurlText.movementMethod=LinkMovementMethod.getInstance()

    }

    @OnClick
    protected fun onDetailClick() {
        onItemClick(tag)
    }
}