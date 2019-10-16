package com.tregz.miksing.home.work

import com.tregz.miksing.data.DataModel

object WorkCollection {

    val list = mutableListOf<DataModel.Work>()
    private val set = mutableSetOf<DataModel.Work>()
    private val map = mutableMapOf<Int, DataModel.Work>()

    val listCount: Int
        get() = list.size

    val setCount: Int
        get() = set.size

    fun add(work: DataModel.Work): DataModel.Work {
        list.add(work)
        set.add(work)
        map[map.size] = work
        return work
    }

    fun clear() {
        list.clear()
        set.clear()
        map.clear()
    }
}