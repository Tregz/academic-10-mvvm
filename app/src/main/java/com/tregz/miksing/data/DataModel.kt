package com.tregz.miksing.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

sealed class DataModel(
    open var _key: String? = null,
    var born: Date? = null,
    open val copy: Date
) {

    // TODO abstract class Item()

    // TODO abstract class Join()

    @Parcelize
    data class Song(
        override val copy: Date,
        var kind: Int = 0,
        var last: String? = null,
        var mark: String? = null,
        var name: String? = null
    ) : DataModel(copy = copy), Parcelable {

        companion object {
            val TAG = Song::class.java.simpleName
        }
    }

    @Parcelize
    data class User(
        override val copy: Date,
        var auth: String? = null
    ) : DataModel(copy = copy), Parcelable {

        companion object {
            val TAG = User::class.java.simpleName
        }
    }

}