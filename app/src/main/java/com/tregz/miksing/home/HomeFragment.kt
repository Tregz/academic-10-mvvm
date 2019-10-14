package com.tregz.miksing.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tregz.miksing.R
import com.tregz.miksing.base.BaseFragment
import com.tregz.miksing.data.song.title
import com.tregz.miksing.home.song.SongCollection
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        log.text = ""
        for (song in SongCollection.list) {
            log.append("${song.title}\n")
            // TODO: more info
        }
    }
}