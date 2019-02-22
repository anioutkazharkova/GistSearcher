package com.azharkova.gistsearcher.data

import android.os.Parcel
import android.os.Parcelable
import com.azharkova.gistsearcher.util.extensions.readMap
import com.azharkova.gistsearcher.util.extensions.readMapLoading
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.locks.AbstractOwnableSynchronizer
import kotlin.collections.HashMap

data class GistInfo(@SerializedName("owner") val owner:GistUser,
                    @SerializedName("id") val id:String,
                    @SerializedName("url") val url:String?,
                    @SerializedName("description") val description: String?,
                    @SerializedName("commits_url") val commitsUrl:String?,
                    @SerializedName("files") val files: Map<String,GistFile>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(GistUser::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readMapLoading(tempMap,GistFile::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(owner, flags)
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(commitsUrl)
        parcel.writeMap(files)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GistInfo> {
        private var tempMap: HashMap<String,GistFile> = HashMap<String,GistFile>()
        override fun createFromParcel(parcel: Parcel): GistInfo {
            return GistInfo(parcel)
        }

        override fun newArray(size: Int): Array<GistInfo?> {
            return arrayOfNulls(size)
        }
    }
}
