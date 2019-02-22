package com.azharkova.gistsearcher.util.extensions

import android.content.Context
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


fun CircleImageView.load(path: String) {
    Picasso.get().load(path).into(this)
}