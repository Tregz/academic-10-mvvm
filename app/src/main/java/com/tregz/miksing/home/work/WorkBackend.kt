package com.tregz.miksing.home.work

import androidx.lifecycle.ViewModel
import com.tregz.miksing.data.DataModel
import com.tregz.miksing.home.HomeView

class WorkBackend(private val listener: HomeView) : ViewModel() {

    fun save(work: DataModel.Work) {
        WorkCollection.add(work)
        listener.saved()
    }

}