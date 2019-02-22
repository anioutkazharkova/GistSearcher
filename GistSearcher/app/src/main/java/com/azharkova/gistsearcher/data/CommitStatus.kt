package com.azharkova.gistsearcher.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CommitStatus(@SerializedName("deletions") val deletions:Int,
                        @SerializedName("additions") val additions:Int,
                        @SerializedName("total") val total:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(deletions)
        parcel.writeInt(additions)
        parcel.writeInt(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommitStatus> {
        override fun createFromParcel(parcel: Parcel): CommitStatus {
            return CommitStatus(parcel)
        }

        override fun newArray(size: Int): Array<CommitStatus?> {
            return arrayOfNulls(size)
        }
    }
}