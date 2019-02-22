package com.azharkova.gistsearcher.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GistUser(@SerializedName("login") val login: String,
                    @SerializedName("id") val id:Int,
                    @SerializedName("avatar_url") val avatarUrl: String?,
                    @SerializedName("url") val url: String?,
@SerializedName("html_url") val htmlUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeInt(id)
        parcel.writeString(avatarUrl)
        parcel.writeString(url)
        parcel.writeString(htmlUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GistUser> {
        override fun createFromParcel(parcel: Parcel): GistUser {
            return GistUser(parcel)
        }

        override fun newArray(size: Int): Array<GistUser?> {
            return arrayOfNulls(size)
        }
    }
}