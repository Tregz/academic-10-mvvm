package com.tregz.miksing.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

sealed class DataModel(
    open var _key: String? = null,
    var born: Date? = null,
    open val copy: Date
) : Parcelable {

    // TODO abstract class Join()

    sealed class Work(
        copy: Date,
        var mark: String? = null,
        var name: String? = null
    ) : DataModel(copy = copy) {

        @Parcelize
        data class Song(
            override val copy: Date,
            var kind: Int = 0,
            var last: String? = null
        ) : DataModel.Work(copy = copy) {

            companion object {
                val TAG = Song::class.java.simpleName
            }
        }

        @Parcelize
        data class Take(override val copy: Date) : DataModel.Work(copy = copy)

    }

    @Parcelize
    data class User(
        override val copy: Date,
        var auth: String? = null
    ) : DataModel(copy = copy) {

        companion object {
            val TAG = User::class.java.simpleName
        }
    }

}