package com.tregz.miksing.data.work.song

import com.tregz.miksing.data.DataModel

var DataModel.Work.Song.dirty : Boolean
    get() = kind >= Kind.UNDEFINED_DIRTY.ordinal
    set(value) {
        if (value && !dirty) kind = Kind.values()[kind].ordinal + 5
        else if (!value && dirty) kind = Kind.values()[kind].ordinal - 5
    }
var DataModel.Work.Song.mixedBy
    get() = last
    set(value) { last = value }

var DataModel.Work.Song.mix
    get() = kind - if (dirty) 5 else 0
    set(value) { kind = value + if (dirty) 5 else 0 }

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