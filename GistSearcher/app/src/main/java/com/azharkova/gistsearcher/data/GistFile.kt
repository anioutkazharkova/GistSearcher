package com.azharkova.gistsearcher.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GistFile(@SerializedName("content") val content: String?,
                    @SerializedName("filename") val filename: String?,
                    @SerializedName("raw_url") val url: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
         parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeString(filename)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GistFile> {
        override fun createFromParcel(parcel: Parcel): GistFile {
            return GistFile(parcel)
        }

        override fun newArray(size: Int): Array<GistFile?> {
            return arrayOfNulls(size)
        }
    }
}