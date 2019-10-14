package com.tregz.miksing.home.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.widget.AppCompatSpinner
import com.tregz.miksing.R
import com.tregz.miksing.base.BaseActivity
import com.tregz.miksing.base.BaseFragment
import com.tregz.miksing.data.DataModel
import com.tregz.miksing.data.song.*
import com.tregz.mvvm.core.date.DateUtil
import kotlinx.android.synthetic.main.fragment_song.*
import java.util.*

class SongFragment : BaseFragment(), AdapterView.OnItemSelectedListener, SongView {

    private val song: DataModel.Song = DataModel.Song(Date())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cb_dirty.setOnCheckedChangeListener { _, checked -> song.dirty = checked }
        et_artist.setOnFocusChangeListener { v, _ -> song.artist = text(v) }
        et_mixed_by.setOnFocusChangeListener { v, _ -> song.mixed_by = text(v) }
        et_release_date.setOnClickListener {
            if (activity is BaseActivity) with((activity as BaseActivity)) {
                val released = song.releasedAt ?: Date()
                group?.let { add(SongDialog(it, this@SongFragment, released)) }
            }
        }
        et_title.setOnFocusChangeListener { v, _ -> song.title = text(v) }
        with(sp_mix_version) {
            dropdown(this, R.array.array_song_mix_version, R.layout.spinner_label)
            onItemSelectedListener = this@SongFragment
        }

        cb_dirty.isChecked = song.dirty
        et_artist.setText(song.artist)
        et_mixed_by.setText(song.mixed_by)
        song.releasedAt?.let { et_release_date.setText(DateUtil.dayOfYear(at = it)) }
        et_title.setText(song.title)
        with(sp_mix_version) { if (song.mix < adapter.count) setSelection(song.mix) }

        //val date = song.createdAt
        /* val id = when (song) {
            is DataModel.Song -> song._key
            is DataModel.User -> ""
        } */
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        song.mix = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // do nothing
    }

    override fun release(date: Date) {
        et_release_date.setText(DateUtil.dayOfYear(at = date))
        song.releasedAt = date
    }

    fun clear() {
        cb_dirty.isChecked = false
        et_artist.setText("")
        et_mixed_by.setText("")
        et_release_date.setText("")
        et_title.setText("")
        sp_mix_version.setSelection(0)
    }

    fun save() {
        SongCollection.add(song)
    }

    private fun text(view: View): String {
        return if (view is EditText) (view as EditText?)?.text.toString() else ""
    }

    private fun dropdown(spinner: AppCompatSpinner, array: Int, layout: Int) {
        context?.let { spinner.adapter = ArrayAdapter.createFromResource(it, array, layout) }
    }
}