package com.tregz.miksing.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.tregz.miksing.R

class HomeLauncher : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        // TODO : remove temp handler which is testing splash screen
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
        }, MOCK_DELAY)
    }

    companion object {
        private const val MOCK_DELAY = 2000L
    }
}