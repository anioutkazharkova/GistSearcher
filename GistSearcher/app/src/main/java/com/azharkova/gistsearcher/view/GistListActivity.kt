package com.azharkova.gistsearcher.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.azharkova.gistsearcher.R
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.presentation.GistListPresenter
import com.azharkova.gistsearcher.view.adapters.GistListAdapter
import com.azharkova.gistsearcher.view.adapters.UsersAdapter
import javax.inject.Inject

class GistListActivity : BaseActivity(),IGistListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: GistListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var adapter: GistListAdapter
    private lateinit var usersAdapter: UsersAdapter

    private lateinit var unbinder: Unbinder

    @BindView(R.id.gistsList)
    protected lateinit var gistsList: RecyclerView

    @BindView(R.id.usersList)
    protected lateinit var usersList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_list)
        setTitle(R.string.text_activity_gist_list_title)

        unbinder = ButterKnife.bind(this)
        adapter = GistListAdapter { presenter.selectGist(it) }
        usersAdapter = UsersAdapter { presenter.selectUser(it) }

        gistsList.layoutManager = LinearLayoutManager(this)
        usersList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        presenter.getGists()
    }

    override fun setupGistsList(data: List<GistInfo>) {
        gistsList.adapter = adapter
        adapter.setData(data)
    }

    override fun setupUsers(users: List<GistUser>) {
        usersList.adapter = usersAdapter
        usersAdapter.setData(users)
    }

    override fun openGist(item: GistInfo) {
        startActivity(GistItemActivity.newIntent(this, item))
    }

    override fun openUserGists(gists: List<GistInfo>, user: GistUser) {
        startActivity(GistUserActivity.newIntent(this, gists, user))
    }

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }
}
