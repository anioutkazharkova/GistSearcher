package com.azharkova.gistsearcher.view

import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistFile
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.presentation.GistItemPresenter
import com.azharkova.gistsearcher.presentation.GistListPresenter
import com.azharkova.gistsearcher.util.ExtraKeys
import com.azharkova.gistsearcher.util.extensions.load
import com.azharkova.gistsearcher.view.adapters.GistCommitsAdapter
import com.azharkova.gistsearcher.view.adapters.GistFileAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_gist_item.*
import javax.inject.Inject

class GistItemActivity :BaseActivity(),IGistItemView {

    companion object {
        fun newIntent(context: Context, item: GistInfo): Intent {
            val intent = Intent(context,GistItemActivity::class.java)
            intent.putExtra(ExtraKeys.ExtraGist,item)
            return  intent
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: GistItemPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var commitsAdapter: GistCommitsAdapter
    private lateinit var filesAdapter: GistFileAdapter

    @BindView(R.id.commitsList)
    protected lateinit var gistsCommitsList: RecyclerView

    @BindView(R.id.filesList)
    protected lateinit var filesList: RecyclerView

    @BindView(R.id.image_avatar)
    protected lateinit var avatarImage: CircleImageView

    @BindView(R.id.text_username)
    protected lateinit var usernameText:TextView

    @BindView(R.id.text_gist_title)
    protected lateinit var gistText: TextView

    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.text_activity_gist_item_title)

        unbinder = ButterKnife.bind(this)
        commitsAdapter = GistCommitsAdapter {  }
        filesAdapter = GistFileAdapter {  }
        gistsCommitsList.layoutManager = LinearLayoutManager(this)
        filesList.layoutManager = LinearLayoutManager(this)
        presenter.setupGist(intent.getParcelableExtra<GistInfo>(ExtraKeys.ExtraGist))
    }

    override fun setupGist(item: GistInfo) {
gistText.text = item.description
        usernameText.text = item.owner.login
        item.owner.avatarUrl?.let {
            avatarImage.load(it)
        }

        filesList.adapter = filesAdapter
        filesAdapter.setData(item.files.values.toList())
    }

    override fun setupCommits(commits: List<GistCommitInfo>) {
        commitsList.adapter = commitsAdapter
        commitsAdapter.setData(commits)
    }
}
