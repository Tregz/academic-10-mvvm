package com.tregz.miksing.home.song

import com.tregz.miksing.data.DataModel

object SongCollection {

    val list = mutableListOf<DataModel.Song>()
    private val set = mutableSetOf<DataModel.Song>()
    private val map = mutableMapOf<Int, DataModel.Song>()

    val listCount: Int
        get() = list.size

    val setCount: Int
        get() = set.size

    fun add(apple: DataModel.Song): DataModel.Song {
        list.add(apple)
        set.add(apple)
        map[map.size] = apple
        return apple
    }

    fun clear() {
        list.clear()
        set.clear()
        map.clear()
    }
}