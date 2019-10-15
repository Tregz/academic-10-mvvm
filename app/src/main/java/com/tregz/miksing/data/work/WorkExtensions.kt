package com.tregz.miksing.data.work

import com.tregz.miksing.data.DataModel

var DataModel.Work.artist: String?
    get() = mark
    set(value) { mark = value }

var DataModel.Work.releasedAt
    get() = born
    set(value) { born = value }

var DataModel.Work.title
    get() = name
    set(value) { name = value }