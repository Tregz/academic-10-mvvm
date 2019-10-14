package com.tregz.miksing.home.song

import android.view.ViewGroup
import android.widget.DatePicker
import com.tregz.miksing.R
import com.tregz.miksing.base.BaseDialog
import java.util.*

class SongDialog(group: ViewGroup, listener: SongView, private var date: Date) :
    BaseDialog(group.context), DatePicker.OnDateChangedListener {

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        date = Calendar.getInstance().let { cal ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.time
        }
    }

    init {
        builder.setTitle(R.string.song_release_date)
        val view = inflate(R.layout.picker_date, group)
        Calendar.getInstance().apply {
            time = date
            val year = get(Calendar.YEAR)
            val monthOfYear = get(Calendar.MONTH)
            val dayOfMonth = get(Calendar.DAY_OF_MONTH)
            with(view.findViewById<DatePicker>(R.id.picker_date)) {
                init(year, monthOfYear, dayOfMonth, this@SongDialog)
            }
        }
        builder.setView(view)
        builder.setPositiveButton(okay) { _, _ -> listener.release(date) }
        show()
    }
}