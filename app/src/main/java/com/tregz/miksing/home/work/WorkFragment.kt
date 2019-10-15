package com.tregz.miksing.home.work

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProviders
import com.tregz.miksing.R
import com.tregz.miksing.arch.bind.BindFactory
import com.tregz.miksing.base.BaseActivity
import com.tregz.miksing.base.BaseFragment
import com.tregz.miksing.data.DataModel
import com.tregz.miksing.data.work.artist
import com.tregz.miksing.data.work.releasedAt
import com.tregz.miksing.data.work.song.dirty
import com.tregz.miksing.data.work.song.mix
import com.tregz.miksing.data.work.song.mixedBy
import com.tregz.miksing.data.work.title
import com.tregz.mvvm.core.date.DateUtil
import kotlinx.android.synthetic.main.fragment_work.*
import kotlinx.android.synthetic.main.fragment_work.cb_dirty
import kotlinx.android.synthetic.main.fragment_work.et_mixed_by
import kotlinx.android.synthetic.main.fragment_work.sp_mix_version
import java.util.*

/** Fragment for editing [DataModel.Work.Song] or [DataModel.Work.Take] item */
class WorkFragment : BaseFragment(), AdapterView.OnItemSelectedListener, WorkView {

    private val backend: WorkBackend by lazy {
        with(ViewModelProviders.of(this, BindFactory { WorkBackend(listener) })) {
            get(WorkBackend::class.java)
        }
    }

    private var dirty: Boolean = false
    private var mix: Int = 0
    private var releasedAt: Date? = null
    private var type: Work = Work.values()[0]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // UI listeners
        et_artist.setOnFocusChangeListener { v, _ -> /* TODO validate, artist = text(v) */ }
        et_release_date.setOnClickListener {
            if (activity is BaseActivity) with((activity as BaseActivity)) {
                val released = releasedAt ?: Date()
                group?.let { add(WorkDialog(it, this@WorkFragment, released)) }
            }
        }
        et_title.setOnFocusChangeListener { v, _ -> /* TODO validate, title = text(v) */ }
        with(sp_work_type) {
            dropdown(this, R.array.array_work_type)
            onItemSelectedListener = this@WorkFragment
        }

        // Update UI
        // TODO et_artist.setText(artist)
        releasedAt?.let { et_release_date.setText(DateUtil.dayOfYear(at = it)) }
        // TODO et_title.setText(title)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.sp_work_type -> {
                with (position == Work.SONG.ordinal) {
                    song_details.visibility = if (this) VISIBLE else GONE
                    if (this) {

                        // UI listeners
                        cb_dirty.setOnCheckedChangeListener { _, checked -> dirty = checked }
                        et_mixed_by.setOnFocusChangeListener { v, _ ->
                            /* TODO validate, mixedBy = text(v) */ }
                        with(sp_mix_version) {
                            dropdown(this, R.array.array_song_mix_version)
                            onItemSelectedListener = this@WorkFragment
                        }

                        // Update UI
                        cb_dirty.isChecked = dirty
                        // TODO et_mixed_by.setText(mixedBy)
                        with(sp_mix_version) { if (mix < adapter.count) setSelection(mix) }
                    }
                }
            }
            R.id.sp_mix_version -> mix = position
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
// do nothing
    }

    override fun release(date: Date) {
        et_release_date.setText(DateUtil.dayOfYear(at = date))
        releasedAt = date
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
        val work = when(type) {
            Work.SONG -> DataModel.Work.Song(Date())
            Work.TAKE -> DataModel.Work.Take(Date())
        }
        work.artist = text(et_artist)
        work.releasedAt = releasedAt
        work.title = text(et_title)
        if (work is DataModel.Work.Song) {
            work.dirty = dirty
            work.mix = mix
            work.mixedBy = text(et_mixed_by)
        }
        backend.save(work)
    }

    private fun text(view: View): String {
        // TODO validate input
        return if (view is EditText) (view as EditText?)?.text.toString() else ""
    }

    private fun dropdown(spinner: AppCompatSpinner, array: Int) {
        context?.let {
            spinner.adapter = ArrayAdapter.createFromResource(it, array, R.layout.spinner_label)
        }
    }

    private enum class Work {
        TAKE,
        SONG
    }
}