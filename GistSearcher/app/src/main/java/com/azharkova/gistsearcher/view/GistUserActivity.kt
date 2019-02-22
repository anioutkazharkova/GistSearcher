package com.azharkova.gistsearcher.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.presentation.GistListPresenter
import com.azharkova.gistsearcher.presentation.GistUserPresenter
import com.azharkova.gistsearcher.util.ExtraKeys
import com.azharkova.gistsearcher.util.extensions.load
import com.azharkova.gistsearcher.view.adapters.GistListAdapter
import com.azharkova.gistsearcher.view.adapters.UsersAdapter
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList
import javax.inject.Inject

class GistUserActivity : BaseActivity(),IGistUserView {

    companion object {
        fun newIntent(context:Context,gists:List<GistInfo>,user: GistUser):Intent
        {
            val intent = Intent(context,GistUserActivity::class.java)
            intent.putParcelableArrayListExtra(ExtraKeys.ExtraGistList,gists as ArrayList<GistInfo>)
            intent.putExtra(ExtraKeys.ExtraUser,user)
            return intent
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: GistUserPresenter

    @ProvidePresenter
    fun providePresenter() = presenter
    private lateinit var unbinder: Unbinder

    private lateinit var adapter: GistListAdapter

    @BindView(R.id.gistsList)
    protected lateinit var gistsList: RecyclerView

    @BindView(R.id.image_avatar)
    protected lateinit var avatarImage: CircleImageView

    @BindView(R.id.text_username)
    protected lateinit var usernameText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.text_activity_gist_user_title)

        unbinder = ButterKnife.bind(this)
        adapter = GistListAdapter {  }
        adapter.simplified = true

        gistsList.layoutManager = LinearLayoutManager(this)
        presenter.setupGists(intent.getParcelableArrayListExtra(ExtraKeys.ExtraGistList))
        presenter.user = intent.getParcelableExtra(ExtraKeys.ExtraUser)
    }

    override fun setupGistsList(data: List<GistInfo>) {
        gistsList.adapter = adapter
        adapter.simplified = true
        adapter.setData(data)
    }

    override fun setupUser(user: GistUser) {
        usernameText.text = user.login
       user.avatarUrl?.let {
            avatarImage.load(it)
        }
    }
}
