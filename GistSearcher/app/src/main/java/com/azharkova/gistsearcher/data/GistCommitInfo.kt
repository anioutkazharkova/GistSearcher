package com.azharkova.gistsearcher.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GistCommitInfo(@SerializedName("owner") val owner:GistUser,
                          @SerializedName("url") val url:String,
                          @SerializedName("version") val version: String,
                          @SerializedName("change_status") val status: CommitStatus) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(GistUser::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(CommitStatus::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(owner, flags)
        parcel.writeString(url)
        parcel.writeString(version)
        parcel.writeParcelable(status, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GistCommitInfo> {
        override fun createFromParcel(parcel: Parcel): GistCommitInfo {
            return GistCommitInfo(parcel)
        }

        override fun newArray(size: Int): Array<GistCommitInfo?> {
            return arrayOfNulls(size)
        }
    }
}