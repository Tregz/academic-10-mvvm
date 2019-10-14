package com.tregz.miksing.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tregz.miksing.R
import com.tregz.miksing.base.BaseActivity
import com.tregz.miksing.home.song.SongFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            if (!back()) {
                HomeNavigation.navigate(this, R.id.action_homeFragment_to_songFragment)
                fab.isExpanded = !fab.isExpanded
                (it as FloatingActionButton).setImageResource(R.drawable.ic_close)
            }
        }
        clear_all.setOnClickListener {
            // TODO dialog
            with(HomeNavigation.primary(this)) { if (this is SongFragment) clear() }
        }
        save.setOnClickListener {
            with(HomeNavigation.primary(this)) { if (this is SongFragment) save() }
            back()
        }
    }

    override fun onBackPressed() {
        back()
    }

    private fun back(): Boolean {
        return (HomeNavigation.fragmentId(this) != R.id.homeFragment).apply {
            if (this) {
                HomeNavigation.pop(this@HomeActivity)
                fab.isExpanded = !fab.isExpanded
                (fab as FloatingActionButton).setImageResource(R.drawable.ic_add)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
