package com.tregz.miksing.data.song

import com.tregz.miksing.data.DataModel

var DataModel.Song.artist: String?
    get() = mark
    set(value) { mark = value }
val DataModel.Song.createdAt
    get() = copy
var DataModel.Song.dirty : Boolean
    get() = kind >= Kind.UNDEFINED_DIRTY.ordinal
    set(value) {
        if (value && !dirty) kind = Kind.values()[kind].ordinal + 5
        else if (!value && dirty) kind = Kind.values()[kind].ordinal - 5
    }
var DataModel.Song.id
    get() = _key
    set(value) { _key = value }
var DataModel.Song.mixed_by
    get() = last
    set(value) { last = value }
var DataModel.Song.mix
    get() = kind - if (dirty) 5 else 0
    set(value) { kind = value + if (dirty) 5 else 0 }
var DataModel.Song.releasedAt
    get() = born
    set(value) { born = value }
var DataModel.Song.title
    get() = name
    set(value) { name = value }

private enum class Kind {
    UNDEFINED,
    MIX_CLEAN,
    EXTENDED_CLEAN,
    REMIX_CLEAN,
    REMIX_EXTENDED_CLEAN,
    UNDEFINED_DIRTY,
    MIX_DIRTY,
    EXTENDED_DIRTY,
    REMIX_DIRTY,
    REMIX_EXTENDED_DIRTY,
}